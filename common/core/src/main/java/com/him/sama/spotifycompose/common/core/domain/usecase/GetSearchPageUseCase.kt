package com.him.sama.spotifycompose.common.core.domain.usecase

import arrow.core.Either
import com.him.sama.spotifycompose.common.core.domain.model.SearchPageDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchPageUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(): Flow<Either<UserError, SearchPageDomainModel>> {
        return searchRepository.fetchSearchPage()
    }
}