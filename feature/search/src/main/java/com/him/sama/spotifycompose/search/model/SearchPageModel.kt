package com.him.sama.spotifycompose.search.model

data class SearchPageModel(
    val categories: List<StoryItemModel> = listOf(),
    val story: List<CategoriesItemModel?> = listOf()
)

data class StoryItemModel(
    val image: String,
    val title: String
)

data class CategoriesItemModel(
    val bgColor: String,
    val image: String,
    val title: String
)
