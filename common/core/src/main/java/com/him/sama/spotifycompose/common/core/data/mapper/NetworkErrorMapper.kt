package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.nonFatalOrThrow
import com.google.gson.Gson
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.data.remote.model.ErrorResponse
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

internal class NetworkErrorMapper @Inject constructor(private val errorResponseJsonAdapter: Gson) :
    Mapper<Throwable, UserError> {

    override fun invoke(param: Throwable): UserError {
        val throwable = param.nonFatalOrThrow()

        return runCatching {
            when (throwable) {
                is UserError -> throwable
                is IOException -> when (throwable) {
                    is UnknownHostException -> UserError.NetworkError
                    is SocketTimeoutException -> UserError.NetworkError
                    is SocketException -> UserError.NetworkError
                    else -> UserError.NetworkError
                }

                is HttpException ->
                    throwable.response()!!
                        .takeUnless { it.isSuccessful }!!
                        .errorBody()!!
                        .use(ResponseBody::string)
                        .let { mapResponseError(it) }

                else -> UserError.Unexpected
            }
        }.getOrElse {
            it.nonFatalOrThrow()
            UserError.Unexpected
        }
    }

    @Throws(Throwable::class)
    private fun mapResponseError(json: String): UserError {
        val errorResponse = errorResponseJsonAdapter.fromJson(json, ErrorResponse::class.java)!!

        return when (errorResponse.error) {
            "internal-error" -> UserError.ServerError
            else -> UserError.Unexpected
        }
    }
}
