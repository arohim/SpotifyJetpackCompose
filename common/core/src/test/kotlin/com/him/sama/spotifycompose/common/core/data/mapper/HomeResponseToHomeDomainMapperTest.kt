package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.getOrElse
import arrow.core.right
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeLayoutType
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseDetailItem
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainDetailItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainLayoutType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeResponseToHomeDomainMapperTest {

    private lateinit var mapper: HomeResponseToHomeDomainMapper

    @Before
    fun setup() {
        mapper = HomeResponseToHomeDomainMapper()
    }

    @Test
    fun `map HomeResponseItem to HomeDomainItem`() {
        // Given
        val responseItem = createMockHomeResponseItem()

        // When
        val result = mapper(responseItem)

        // Then
        val expectedDomainItem = createExpectedDomainItem()
        assertEquals(expectedDomainItem.right(), result)
    }

    private fun createMockHomeResponseItem(): HomeResponseItem {
        return HomeResponseItem(
            layoutType = HomeLayoutType.GRID,
            title = "Banner Title",
            image = "banner_image.jpg",
            items = listOf(
                HomeResponseDetailItem(
                    image = "item_image.jpg",
                    title = "Item Title",
                    description = "Item Description",
                    categoryHierarchy = "Category Hierarchy",
                    categoryName = "Category Name"
                )
            )
        )
    }

    private fun createExpectedDomainItem(): HomeDomainItem {
        return HomeDomainItem.create(
            layoutType = HomeDomainLayoutType.GRID,
            title = "Banner Title",
            image = "banner_image.jpg",
            items = listOf(
                HomeDomainDetailItem.create(
                    image = "item_image.jpg",
                    title = "Item Title",
                    description = "Item Description",
                    categoryHierarchy = "Category Hierarchy",
                    categoryName = "Category Name"
                ).getOrElse { error("") }
            )
        ).getOrElse { error("") }
    }
}
