package com.him.sama.spotifycompose.common.core.data.repository

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.left
import arrow.core.right
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.base.dispatcher.AppCoroutineDispatchers
import com.him.sama.spotifycompose.common.core.data.remote.model.CategoriesItem
import com.him.sama.spotifycompose.common.core.data.remote.model.SearchPageResponse
import com.him.sama.spotifycompose.common.core.data.remote.model.StoryItem
import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import com.him.sama.spotifycompose.common.core.domain.model.CategoryDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.SearchPageDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.StoryItemDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.testutil.TestCoroutineDispatcherRule
import com.him.sama.testutil.TestDispatchers
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import timber.log.Timber
import java.io.IOException

@ExperimentalCoroutinesApi
class SearchRepositoryImplTest {

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()
    private val testDispatcher get() = coroutineRule.testCoroutineDispatcher

    private lateinit var apiService: ApiService
    private lateinit var dispatchers: AppCoroutineDispatchers
    private lateinit var responseToDomain: Mapper<SearchPageResponse, Either<NonEmptyList<Void>, SearchPageDomainModel>>
    private lateinit var errorMapper: Mapper<Throwable, UserError>
    private lateinit var searchRepository: SearchRepositoryImpl

    @Before
    fun setup() {
        apiService = mockk()
        dispatchers = mockk()
        responseToDomain = mockk()
        errorMapper = mockk()

        searchRepository = SearchRepositoryImpl(
            apiService = apiService,
            dispatchers = TestDispatchers(testDispatcher),
            responseToDomain = responseToDomain,
            errorMapper = errorMapper
        )

        // Mocking the static method
        mockkObject(Timber)
    }

    @Test
    fun `fetchSearchPage success`() = runTest {
        // Given
        val expectedDomainModel = SearchPageDomainModel(
            categories = listOf(
                CategoryDomainModel(bgColor = "bgColor", image = "image", title = "title")
            ),
            story = listOf(
                StoryItemDomainModel(image = "image", title = "title")
            )
        )
        val response = SearchPageResponse(
            categories = listOf(
                CategoriesItem(bgColor = "bgColor", image = "image", title = "title")
            ),
            story = listOf(
                StoryItem(image = "image", title = "title")
            )
        )

        coEvery { apiService.getSearch() } returns response
        coEvery { responseToDomain(response) } returns expectedDomainModel.right()

        // When
        val result = searchRepository.fetchSearchPage().first()

        // Then
        assertEquals(expectedDomainModel.right(), result)
    }

    @Test
    fun `fetchSearchPage failure`() = runTest {
        // Given
        val expectedError = UserError.NetworkError
        val exception = IOException("Network Error")

        coEvery { apiService.getSearch() } throws exception
        coEvery { errorMapper(any()) } returns expectedError

        // When
        val result = searchRepository.fetchSearchPage().firstOrNull()

        // Then
        assertEquals(expectedError.left(), result)
    }
}
