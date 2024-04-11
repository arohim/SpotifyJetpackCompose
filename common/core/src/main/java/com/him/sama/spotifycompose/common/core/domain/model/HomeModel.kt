package com.him.sama.spotifycompose.common.core.domain.model

import arrow.core.EitherNel
import arrow.core.right

enum class HomeDomainLayoutType {
    GRID,
    ALBUM,
    PLAY_WIDGET
}

data class HomeDomainItem(
    val layoutType: HomeDomainLayoutType,
    val title: String,
    val image: String? = null,
    val items: List<HomeDomainDetailItem> = listOf()
) {
    companion object {
        fun create(
            layoutType: HomeDomainLayoutType?,
            title: String,
            image: String? = null,
            items: List<HomeDomainDetailItem>
        ): EitherNel<HomeValidationError, HomeDomainItem> {
            return HomeDomainItem(
                layoutType = layoutType ?: HomeDomainLayoutType.ALBUM,
                title = title,
                image = image,
                items = items
            ).right()
        }
    }
}

data class HomeDomainDetailItem(
    val image: String,
    val title: String,
    val description: String? = null,
    val categoryHierarchy: String,
    val categoryName: String
) {
    companion object {
        fun create(
            image: String,
            title: String,
            description: String? = null,
            categoryHierarchy: String,
            categoryName: String
        ): EitherNel<HomeValidationError, HomeDomainDetailItem> {
            return HomeDomainDetailItem(
                image = image,
                title = title,
                description = description,
                categoryHierarchy = categoryHierarchy,
                categoryName = categoryName
            ).right()
        }
    }
}