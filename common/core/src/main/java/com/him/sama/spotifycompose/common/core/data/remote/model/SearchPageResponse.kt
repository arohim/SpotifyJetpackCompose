package com.him.sama.spotifycompose.common.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchPageResponse(

    @field:SerializedName("categories")
    val categories: List<CategoryItem> = listOf(),

    @field:SerializedName("story")
    val story: List<StoryItem> = listOf()
)

data class StoryItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("title")
    val title: String
)

data class CategoryItem(

    @field:SerializedName("bg_color")
    val bgColor: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("title")
    val title: String
)
