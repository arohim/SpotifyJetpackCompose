package com.him.sama.spotifycompose.common.core.domain.usecase

import arrow.core.Either
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeModelItem
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Flow<Either<UserError, List<HomeModelItem>>> {
        return homeRepository.fetchHomeData()
    }
}