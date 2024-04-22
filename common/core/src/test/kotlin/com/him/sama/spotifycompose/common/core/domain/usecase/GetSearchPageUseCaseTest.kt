package com.him.sama.spotifycompose.common.core.domain.usecase

import arrow.core.left
import arrow.core.right
import com.him.sama.spotifycompose.common.core.domain.model.SearchPageDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.repository.SearchRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetSearchPageUseCaseTest {

    private lateinit var searchRepository: SearchRepository
    private lateinit var useCase: GetSearchPageUseCase

    @Before
    fun setup() {
        searchRepository = mockk(relaxed = true)
        useCase = GetSearchPageUseCase(searchRepository)
    }

    @Test
    fun `invoke success`() = runTest {
        // Given
        val expectedData = mockk<SearchPageDomainModel>()
        coEvery { searchRepository.fetchSearchPage() } returns flowOf(expectedData.right())

        // When
        val result = useCase().first()

        // Then
        coVerify(exactly = 1) { searchRepository.fetchSearchPage() }
        assertEquals(expectedData.right(), result)
    }

    @Test
    fun `invoke failure`() = runTest {
        // Given
        val expectedError = UserError.NetworkError
        coEvery { searchRepository.fetchSearchPage() } returns flowOf(expectedError.left())

        // When
        val result = useCase().first()

        // Then
        coVerify(exactly = 1) { searchRepository.fetchSearchPage() }
        assertEquals(expectedError.left(), result)
    }
}
