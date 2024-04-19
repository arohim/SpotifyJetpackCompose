package com.him.sama.spotifycompose.common.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class YourLibraryResponse(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("items")
    val items: List<YourLibraryItem>
)

data class YourLibraryItem(

    @field:SerializedName("category_hierarchy")
    val categoryHierarchy: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("title")
    val title: String
)
