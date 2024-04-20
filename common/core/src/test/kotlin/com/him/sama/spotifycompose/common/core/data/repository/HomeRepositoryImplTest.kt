package com.him.sama.spotifycompose.common.core.data.repository

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.left
import arrow.core.right
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.base.dispatcher.AppCoroutineDispatchers
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeLayoutType
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseDetailItem
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainDetailItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainLayoutType
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.testutil.TestCoroutineDispatcherRule
import com.him.sama.testutil.TestDispatchers
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import timber.log.Timber

@ExperimentalCoroutinesApi
class HomeRepositoryImplTest {

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()
    private val testDispatcher get() = coroutineRule.testCoroutineDispatcher

    private lateinit var apiService: ApiService
    private lateinit var dispatchers: AppCoroutineDispatchers
    private lateinit var responseToDomain: Mapper<HomeResponseItem, Either<NonEmptyList<Void>, HomeDomainItem>>
    private lateinit var errorMapper: Mapper<Throwable, UserError>
    private lateinit var homeRepository: HomeRepositoryImpl

    @Before
    fun setup() {
        apiService = mockk()
        dispatchers = mockk()
        responseToDomain = mockk()
        errorMapper = mockk()

        homeRepository = HomeRepositoryImpl(
            apiService,
            TestDispatchers(coroutineRule.testCoroutineDispatcher),
            responseToDomain,
            errorMapper
        )

        // Mocking the static method
        mockkObject(Timber)
    }

    @Test
    fun `fetch home data success`() = runTest {
        // Given
        val expectedDomainItem = HomeDomainItem(
            layoutType = HomeDomainLayoutType.GRID,
            title = "title",
            image = "image_url",
            items = listOf(
                HomeDomainDetailItem(
                    image = "image",
                    title = "title",
                    description = "description",
                    categoryHierarchy = "categoryHierarchy",
                    categoryName = "categoryName"
                )
            )
        )
        val responseItem = HomeResponseItem(
            layoutType = HomeLayoutType.GRID,
            title = "title",
            image = "image",
            items = listOf(
                HomeResponseDetailItem(
                    image = "image",
                    title = "title",
                    description = "description",
                    categoryHierarchy = "categoryHierarchy",
                    categoryName = "categoryName"
                )
            )
        )

        coEvery { apiService.getHome() } returns listOf(responseItem)
        coEvery { responseToDomain(responseItem) } returns expectedDomainItem.right()

        // When
        val result = homeRepository.fetchHomeData().firstOrNull()?.getOrNull()

        // Then
        assertEquals(listOf(expectedDomainItem), result)
    }

    @Test
    fun `fetch home data failure`() = runTest {
        // Given
        val expectedError = UserError.NetworkError
        val exception = IOException("Network Error")

        coEvery { apiService.getHome() } throws exception
        coEvery { errorMapper(any()) } returns expectedError

        // When
        val result = homeRepository.fetchHomeData().firstOrNull()

        // Then
        assertEquals(expectedError.left(), result)
    }
}
