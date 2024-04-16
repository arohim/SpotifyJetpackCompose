package com.him.sama.spotifycompose.common.core.domain.repository

import arrow.core.Either
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseDetailItem
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel
import kotlinx.coroutines.flow.Flow

interface YourLibraryRepository {

    suspend fun fetchYourLibrary(): Flow<Either<UserError, YourLibraryDomainModel>>

}