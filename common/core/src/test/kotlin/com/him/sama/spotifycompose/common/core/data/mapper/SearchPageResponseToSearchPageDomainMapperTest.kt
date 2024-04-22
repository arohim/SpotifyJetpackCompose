package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.getOrElse
import arrow.core.right
import com.him.sama.spotifycompose.common.core.data.remote.model.CategoryItem
import com.him.sama.spotifycompose.common.core.data.remote.model.SearchPageResponse
import com.him.sama.spotifycompose.common.core.data.remote.model.StoryItem
import com.him.sama.spotifycompose.common.core.domain.model.CategoryDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.SearchPageDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.StoryItemDomainModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchPageResponseToSearchPageDomainMapperTest {

    private lateinit var mapper: SearchPageResponseToSearchPageDomainMapper

    @Before
    fun setup() {
        mapper = SearchPageResponseToSearchPageDomainMapper()
    }

    @Test
    fun `map SearchPageResponse to SearchPageDomainModel`() {
        // Given
        val response = createMockSearchPageResponse()

        // When
        val result = mapper(response)

        // Then
        val expectedDomainModel = createExpectedSearchPageDomainModel()
        assertEquals(expectedDomainModel.right(), result)
    }

    private fun createMockSearchPageResponse(): SearchPageResponse {
        return SearchPageResponse(
            categories = listOf(
                CategoryItem(
                    bgColor = "#FFFFFF",
                    title = "Category 1",
                    image = "category1.jpg"
                ),
                CategoryItem(
                    bgColor = "#CCCCCC",
                    title = "Category 2",
                    image = "category2.jpg"
                )
            ),
            story = listOf(
                StoryItem(
                    title = "Story 1",
                    image = "story1.jpg"
                ),
                StoryItem(
                    title = "Story 2",
                    image = "story2.jpg"
                )
            )
        )
    }

    private fun createExpectedSearchPageDomainModel(): SearchPageDomainModel {
        return SearchPageDomainModel.create(
            categories = listOf(
                CategoryDomainModel.create(
                    bgColor = "#FFFFFF",
                    title = "Category 1",
                    image = "category1.jpg"
                ).getOrElse { error("") },
                CategoryDomainModel.create(
                    bgColor = "#CCCCCC",
                    title = "Category 2",
                    image = "category2.jpg"
                ).getOrElse { error("") }
            ),
            story = listOf(
                StoryItemDomainModel.create(
                    title = "Story 1",
                    image = "story1.jpg"
                ).getOrElse { error("") },
                StoryItemDomainModel.create(
                    title = "Story 2",
                    image = "story2.jpg"
                ).getOrElse { error("") }
            )
        ).getOrElse { error("") }
    }
}
