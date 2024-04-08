package com.him.sama.spotifycompose.common.core.domain.repository

import arrow.core.Either
import com.him.sama.spotifycompose.common.core.domain.model.HomeModelItem
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun fetchHomeData(): Flow<Either<UserError, List<HomeModelItem>>>

}