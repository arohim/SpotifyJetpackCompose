package com.him.sama.spotifycompose.common.core.domain.model

import arrow.core.EitherNel
import arrow.core.getOrElse
import arrow.core.right
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseDetailItem

enum class HomeLayoutType {
    GRID,
    ALBUM,
    PLAY_WIDGET
}

data class HomeModelItem(
    val layoutType: HomeLayoutType,
    val title: String,
    val items: List<HomeDetailItem> = listOf()
) {
    companion object {
        fun create(
            layoutType: HomeLayoutType?,
            title: String,
            items: List<HomeResponseDetailItem>
        ): EitherNel<HomeValidationError, HomeModelItem> {
            return HomeModelItem(
                layoutType = layoutType ?: HomeLayoutType.ALBUM,
                title = title,
                items = items.map {
                    HomeDetailItem.create(it).getOrElse { error("") }
                }
            ).right()
        }
    }
}

data class HomeDetailItem(
    val image: String,
    val title: String,
    val categoryHierarchy: String,
    val categoryName: String
) {
    companion object {
        fun create(item: HomeResponseDetailItem): EitherNel<HomeValidationError, HomeDetailItem> {
            return HomeDetailItem(
                image = item.image,
                title = item.title,
                categoryHierarchy = item.categoryHierarchy,
                categoryName = item.categoryName
            ).right()
        }
    }
}