package com.him.sama.spotifycompose.common.core.data.remote.model

import com.google.gson.annotations.SerializedName

enum class HomeLayoutType {
    GRID,
    ALBUM,
    PLAY_WIDGET
}

data class HomeResponseItem(

    @field:SerializedName("layout_type")
    val layoutType: HomeLayoutType,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("items")
    val items: List<HomeResponseDetailItem> = listOf()
)

data class HomeResponseDetailItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("category_hierarchy")
    val categoryHierarchy: String,

    @field:SerializedName("category_name")
    val categoryName: String
)
