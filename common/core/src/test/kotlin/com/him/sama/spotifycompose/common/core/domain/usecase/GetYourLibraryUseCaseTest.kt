package com.him.sama.spotifycompose.common.core.domain.usecase
import arrow.core.left
import arrow.core.right
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel
import com.him.sama.spotifycompose.common.core.domain.repository.YourLibraryRepository
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
class GetYourLibraryUseCaseTest {

    private lateinit var yourLibraryRepository: YourLibraryRepository
    private lateinit var useCase: GetYourLibraryUseCase

    @Before
    fun setup() {
        yourLibraryRepository = mockk(relaxed = true)
        useCase = GetYourLibraryUseCase(yourLibraryRepository)
    }

    @Test
    fun `invoke success`() = runTest {
        // Given
        val expectedData = listOf(mockk<YourLibraryDomainModel>())
        coEvery { yourLibraryRepository.fetchYourLibrary() } returns flowOf(expectedData.right())

        // When
        val result = useCase().first()

        // Then
        coVerify(exactly = 1) { yourLibraryRepository.fetchYourLibrary() }
        assertEquals(expectedData.right(), result)
    }

    @Test
    fun `invoke failure`() = runTest {
        // Given
        val expectedError = UserError.NetworkError
        coEvery { yourLibraryRepository.fetchYourLibrary() } returns flowOf(expectedError.left())

        // When
        val result = useCase().first()

        // Then
        coVerify(exactly = 1) { yourLibraryRepository.fetchYourLibrary() }
        assertEquals(expectedError.left(), result)
    }
}
