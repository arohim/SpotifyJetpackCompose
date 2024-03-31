package com.him.sama.spotifycompose.common.core.data.repository

import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : HomeRepository {

    override suspend fun fetchHomeData(): Response<List<HomeResponseItem>> {
        return apiService.home()
    }

}