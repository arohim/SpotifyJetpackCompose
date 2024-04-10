package com.him.sama.spotifycompose.common.core.data.repository

import arrow.core.Either
import arrow.core.EitherNel
import arrow.core.getOrElse
import arrow.core.left
import arrow.core.leftWiden
import arrow.core.right
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.base.dispatcher.AppCoroutineDispatchers
import com.him.sama.spotifycompose.common.core.base.retrySuspend
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeValidationError
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
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

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatchers: AppCoroutineDispatchers,
    private val responseToDomain: Mapper<HomeResponseItem, EitherNel<HomeValidationError, HomeDomainItem>>,
    private val errorMapper: Mapper<Throwable, UserError>
) : HomeRepository {

    private val responseToDomainThrows: (HomeResponseItem) -> HomeDomainItem = { response ->
        responseToDomain(response).let { validated ->
            validated.toEither().getOrElse {
                val t = UserError.NetworkError
                throw t
            }
        }
    }

    private suspend fun getHomeDataFromRemoteWithRetry(): List<HomeDomainItem> {
        return withContext(dispatchers.io) {
            retrySuspend(
                times = 3,
                initialDelay = 500.toDuration(DurationUnit.MILLISECONDS),
                factor = 2.0,
                shouldRetry = { it is IOException }
            ) { times ->
                Timber.d("[HOME_REPO] Retry times=$times")
                apiService
                    .getHome()
                    .map(responseToDomainThrows)
            }
        }
    }

    override suspend fun fetchHomeData(): Flow<Either<UserError, List<HomeDomainItem>>> =
        flow {
            emit(getHomeDataFromRemoteWithRetry())
        }.map {
            val right = it.right()
            right.leftWiden<UserError, Nothing, List<HomeDomainItem>>()
        }.catch {
            logError(it, "getUsers")
            val value: Either<UserError, Nothing> = errorMapper(it).left()
            emit(value)
        }

    private fun logError(t: Throwable, message: String) = Timber.tag(TAG).e(t, message)

    private companion object {
        private val TAG = HomeRepositoryImpl::class.java.simpleName
    }

}