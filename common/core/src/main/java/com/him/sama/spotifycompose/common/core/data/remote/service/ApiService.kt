package com.him.sama.spotifycompose.common.core.data.remote.service

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockBehavior
import co.infinum.retromock.meta.MockResponse
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.data.remote.model.SearchPageResponse
import com.him.sama.spotifycompose.common.core.data.remote.model.YourLibraryResponse
import retrofit2.http.GET

interface ApiService {

    @Mock
    @MockResponse(code = 200, body = "mock/home_page.json")
    @MockBehavior(durationDeviation = 1000, durationMillis = 1000)
    @GET("/home")
    suspend fun getHome(): List<HomeResponseItem>

    @Mock
    @MockResponse(code = 200, body = "mock/search_page.json")
    @MockBehavior(durationDeviation = 1000, durationMillis = 1000)
    @GET("/home")
    suspend fun getSearch(): SearchPageResponse

    @Mock
    @MockResponse(code = 200, body = "mock/your_library_page.json")
    @MockBehavior(durationDeviation = 1000, durationMillis = 1000)
    @GET("/your_library")
    suspend fun getYourLibrary(): List<YourLibraryResponse>

}
