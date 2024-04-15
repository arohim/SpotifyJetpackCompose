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
import com.him.sama.spotifycompose.common.core.data.mapper.SearchPageResponseToSearchPageDomainMapper
import com.him.sama.spotifycompose.common.core.data.remote.model.SearchPageResponse
import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import com.him.sama.spotifycompose.common.core.domain.model.SearchPageDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.repository.SearchRepository
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

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatchers: AppCoroutineDispatchers,
    private val responseToDomain: SearchPageResponseToSearchPageDomainMapper,
    private val errorMapper: Mapper<Throwable, UserError>
) : SearchRepository {

    private val responseToDomainThrows: (SearchPageResponse) -> SearchPageDomainModel =
        { response ->
            responseToDomain(response).let { validated ->
                validated.getOrElse {
                    val t = UserError.NetworkError
                    throw t
                }
            }
        }

    private suspend fun getSearchPageFromRemoteWithRetry(): SearchPageDomainModel {
        return withContext(dispatchers.io) {
            retrySuspend(
                times = 3,
                initialDelay = 500.toDuration(DurationUnit.MILLISECONDS),
                factor = 2.0,
                shouldRetry = { it is IOException }
            ) { times ->
                Timber.d("[SEARCH_REPO] Retry times=$times")
                val search = apiService.getSearch()
                responseToDomainThrows(search)
            }
        }
    }

    override suspend fun fetchSearchPage(): Flow<Either<UserError, SearchPageDomainModel>> {
        return flow {
            emit(getSearchPageFromRemoteWithRetry())
        }.map {
            val right = it.right()
            right.leftWiden<UserError, Nothing, SearchPageDomainModel>()
        }.catch {
            logError(it, "fetchSearchPage")
            val value: Either<UserError, Nothing> = errorMapper(it).left()
            emit(value)
        }
    }

    private fun logError(t: Throwable, message: String) = Timber.tag(TAG).e(t, message)

    private companion object {
        private val TAG = SearchRepositoryImpl::class.java.simpleName
    }

}