package com.him.sama.spotifycompose.common.core.domain.repository

import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import retrofit2.Response

interface HomeRepository {

    suspend fun fetchHomeData(): Response<List<HomeResponseItem>>

}