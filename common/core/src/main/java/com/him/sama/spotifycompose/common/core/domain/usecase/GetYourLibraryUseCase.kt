package com.him.sama.spotifycompose.common.core.domain.usecase

import arrow.core.Either
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel
import com.him.sama.spotifycompose.common.core.domain.repository.YourLibraryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetYourLibraryUseCase @Inject constructor(
    private val yourLibraryRepository: YourLibraryRepository
) {
    suspend operator fun invoke(): Flow<Either<UserError, YourLibraryDomainModel>> {
        return yourLibraryRepository.fetchYourLibrary()
    }
}