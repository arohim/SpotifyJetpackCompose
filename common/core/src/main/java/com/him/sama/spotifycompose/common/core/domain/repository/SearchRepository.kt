package com.him.sama.spotifycompose.common.core.domain.repository

import arrow.core.Either
import arrow.core.EitherNel
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.SearchPageDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun fetchSearchPage(): Flow<Either<UserError, SearchPageDomainModel>>

}