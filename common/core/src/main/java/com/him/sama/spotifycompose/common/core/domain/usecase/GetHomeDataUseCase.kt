package com.him.sama.spotifycompose.common.core.domain.usecase

import arrow.core.Either
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Flow<Either<UserError, List<HomeDomainItem>>> {
        return homeRepository.fetchHomeData()
    }
}