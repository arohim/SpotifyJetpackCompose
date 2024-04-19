package com.him.sama.spotifycompose.common.core.data.repository

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.left
import arrow.core.leftWiden
import arrow.core.right
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.base.dispatcher.AppCoroutineDispatchers
import com.him.sama.spotifycompose.common.core.base.retrySuspend
import com.him.sama.spotifycompose.common.core.data.mapper.YourLibraryPageResponseToYourLibraryPageDomainMapper
import com.him.sama.spotifycompose.common.core.data.remote.model.YourLibraryResponse
import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel
import com.him.sama.spotifycompose.common.core.domain.repository.YourLibraryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class YourLibraryRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatchers: AppCoroutineDispatchers,
    private val responseToDomain: YourLibraryPageResponseToYourLibraryPageDomainMapper,
    private val errorMapper: Mapper<Throwable, UserError>
) : YourLibraryRepository {

    private val responseToDomainThrows: (List<YourLibraryResponse>) -> List<YourLibraryDomainModel> =
        { response ->
            response.map {
                responseToDomain(it).let { validated ->
                    validated.getOrElse {
                        val t = UserError.NetworkError
                        throw t
                    }
                }
            }
        }

    private suspend fun getRemoteWithRetry(): List<YourLibraryDomainModel> {
        return withContext(dispatchers.io) {
            retrySuspend(
                times = 3,
                initialDelay = 500.toDuration(DurationUnit.MILLISECONDS),
                factor = 2.0,
                shouldRetry = { it is IOException }
            ) { times ->
                Timber.d("[YOUR_LIBRARY_REPO] Retry times=$times")
                val search = apiService.getYourLibrary()
                responseToDomainThrows(search)
            }
        }
    }

    override suspend fun fetchYourLibrary(): Flow<Either<UserError, List<YourLibraryDomainModel>>> {
        return flow {
            emit(getRemoteWithRetry())
        }.map {
            val right = it.right()
            right.leftWiden<UserError, Nothing, List<YourLibraryDomainModel>>()
        }.catch {
            logError(it, "fetchYourLibrary")
            val value: Either<UserError, Nothing> = errorMapper(it).left()
            emit(value)
        }
    }

    private fun logError(t: Throwable, message: String) = Timber.tag(TAG).e(t, message)

    private companion object {
        private val TAG = YourLibraryRepositoryImpl::class.java.simpleName
    }

}