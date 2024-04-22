package com.him.sama.spotifycompose.common.core.data.repository

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.getOrElse
import arrow.core.left
import arrow.core.right
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.base.dispatcher.AppCoroutineDispatchers
import com.him.sama.spotifycompose.common.core.data.remote.model.YourLibraryItem
import com.him.sama.spotifycompose.common.core.data.remote.model.YourLibraryResponse
import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryItemDomainModel
import com.him.sama.testutil.TestCoroutineDispatcherRule
import com.him.sama.testutil.TestDispatchers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class YourLibraryRepositoryImplTest {

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()
    private val testDispatcher get() = coroutineRule.testCoroutineDispatcher

    private lateinit var apiService: ApiService
    private lateinit var dispatchers: AppCoroutineDispatchers
    private lateinit var responseToDomain: Mapper<YourLibraryResponse, Either<NonEmptyList<Void>, YourLibraryDomainModel>>
    private lateinit var errorMapper: Mapper<Throwable, UserError>
    private lateinit var yourLibraryRepository: YourLibraryRepositoryImpl

    @Before
    fun setup() {
        apiService = mockk()
        dispatchers = mockk()
        responseToDomain = mockk()
        errorMapper = mockk()

        yourLibraryRepository = YourLibraryRepositoryImpl(
            apiService = apiService,
            dispatchers = TestDispatchers(testDispatcher),
            responseToDomain = responseToDomain,
            errorMapper = errorMapper
        )
    }

    @Test
    fun `fetchYourLibrary success`() = runTest {
        // Given
        val expectedDomainModels = listOf(
            YourLibraryDomainModel(
                title = "title",
                items = listOf(
                    YourLibraryItemDomainModel(
                        categoryHierarchy = "categoryHierarchy",
                        image = "image",
                        title = "title"
                    )
                )
            )
        )
        val response = YourLibraryResponse(
            title = "title",
            items = listOf(
                YourLibraryItem(
                    categoryHierarchy = "categoryHierarchy",
                    image = "image",
                    title = "title"
                )
            )
        )

        coEvery { apiService.getYourLibrary() } returns listOf(response)
        coEvery { responseToDomain(response) } returns expectedDomainModels.first().right()

        // When
        val result = yourLibraryRepository.fetchYourLibrary().first()

        // Then
        assertEquals(expectedDomainModels, result.getOrElse { emptyList() })
    }

    @Test
    fun `fetchYourLibrary failure`() = runTest {
        // Given
        val expectedError = UserError.NetworkError
        val exception = IOException("Network Error")

        coEvery { apiService.getYourLibrary() } throws exception
        coEvery { errorMapper(any()) } returns expectedError

        // When
        val result = yourLibraryRepository.fetchYourLibrary().first()

        // Then
        assertEquals(expectedError.left(), result)
    }
}
