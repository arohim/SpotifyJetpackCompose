package com.him.sama.spotifycompose.common.core.domain.usecase

import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
import retrofit2.Response
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Response<List<HomeResponseItem>> {
        return homeRepository.fetchHomeData()
    }
}