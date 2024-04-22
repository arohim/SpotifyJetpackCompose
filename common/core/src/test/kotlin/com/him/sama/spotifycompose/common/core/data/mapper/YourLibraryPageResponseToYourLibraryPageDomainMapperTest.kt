package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.getOrElse
import arrow.core.right
import com.him.sama.spotifycompose.common.core.data.remote.model.YourLibraryItem
import com.him.sama.spotifycompose.common.core.data.remote.model.YourLibraryResponse
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryItemDomainModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class YourLibraryPageResponseToYourLibraryPageDomainMapperTest {

    private lateinit var mapper: YourLibraryPageResponseToYourLibraryPageDomainMapper

    @Before
    fun setup() {
        mapper = YourLibraryPageResponseToYourLibraryPageDomainMapper()
    }

    @Test
    fun `map YourLibraryResponse to YourLibraryDomainModel`() {
        // Given
        val response = createMockYourLibraryResponse()

        // When
        val result = mapper(response)

        // Then
        val expectedDomainModel = createExpectedYourLibraryDomainModel()
        assertEquals(expectedDomainModel.right(), result)
    }

    private fun createMockYourLibraryResponse(): YourLibraryResponse {
        return YourLibraryResponse(
            title = "Your Library",
            items = listOf(
                YourLibraryItem(
                    categoryHierarchy = "Category Hierarchy 1",
                    image = "item1.jpg",
                    title = "Item 1"
                ),
                YourLibraryItem(
                    categoryHierarchy = "Category Hierarchy 2",
                    image = "item2.jpg",
                    title = "Item 2"
                )
            )
        )
    }

    private fun createExpectedYourLibraryDomainModel(): YourLibraryDomainModel {
        return YourLibraryDomainModel.create(
            title = "Your Library",
            items = listOf(
                YourLibraryItemDomainModel.create(
                    categoryHierarchy = "Category Hierarchy 1",
                    image = "item1.jpg",
                    title = "Item 1"
                ).getOrElse { error("") },
                YourLibraryItemDomainModel.create(
                    categoryHierarchy = "Category Hierarchy 2",
                    image = "item2.jpg",
                    title = "Item 2"
                ).getOrElse { error("") }
            )
        ).getOrElse { error("") }
    }
}
