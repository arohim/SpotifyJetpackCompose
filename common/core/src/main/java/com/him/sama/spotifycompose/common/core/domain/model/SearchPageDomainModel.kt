package com.him.sama.spotifycompose.common.core.domain.model

import arrow.core.EitherNel
import arrow.core.right

data class SearchPageDomainModel(
    val categories: List<CategoryDomainModel> = listOf(),
    val story: List<StoryItemDomainModel?> = listOf()
) {
    companion object {
        fun create(
            categories: List<CategoryDomainModel>,
            story: List<StoryItemDomainModel>
        ): EitherNel<Nothing, SearchPageDomainModel> {
            return SearchPageDomainModel(
                categories = categories,
                story = story
            ).right()
        }
    }
}

data class StoryItemDomainModel(
    val image: String,
    val title: String
) {
    companion object {
        fun create(
            title: String,
            image: String,
        ): EitherNel<Nothing, StoryItemDomainModel> {
            return StoryItemDomainModel(
                title = title,
                image = image
            ).right()
        }
    }
}

data class CategoryDomainModel(
    val bgColor: String,
    val image: String,
    val title: String
) {
    companion object {
        fun create(
            bgColor: String,
            title: String,
            image: String,
        ): EitherNel<Nothing, CategoryDomainModel> {
            return CategoryDomainModel(
                bgColor = bgColor,
                title = title,
                image = image
            ).right()
        }
    }
}
