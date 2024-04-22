package com.him.sama.spotifycompose.common.core.domain.usecase

import arrow.core.left
import arrow.core.right
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
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
class GetHomeDataUseCaseTest {

    private lateinit var homeRepository: HomeRepository
    private lateinit var useCase: GetHomeDataUseCase

    @Before
    fun setup() {
        homeRepository = mockk(relaxed = true)
        useCase = GetHomeDataUseCase(homeRepository)
    }

    @Test
    fun `invoke success`() = runTest {
        // Given
        val expectedData = listOf(mockk<HomeDomainItem>())
        coEvery { homeRepository.fetchHomeData() } returns flowOf(expectedData.right())

        // When
        val result = useCase().first()

        // Then
        coVerify(exactly = 1) { homeRepository.fetchHomeData() }
        assertEquals(expectedData.right(), result)
    }

    @Test
    fun `invoke failure`() = runTest {
        // Given
        val expectedError = UserError.NetworkError
        coEvery { homeRepository.fetchHomeData() } returns flowOf(expectedError.left())

        // When
        val result = useCase().first()

        // Then
        coVerify(exactly = 1) { homeRepository.fetchHomeData() }
        assertEquals(expectedError.left(), result)
    }
}
