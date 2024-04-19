package com.him.sama.spotifycompose.common.core.domain.model

import arrow.core.EitherNel
import arrow.core.right

data class YourLibraryDomainModel(
    val title: String,
    val items: List<YourLibraryItemDomainModel>
) {
    companion object {
        fun create(
            title: String,
            items: List<YourLibraryItemDomainModel>
        ): EitherNel<Nothing, YourLibraryDomainModel> {
            return YourLibraryDomainModel(
                title = title,
                items = items
            ).right()
        }
    }
}

data class YourLibraryItemDomainModel(
    val categoryHierarchy: String,
    val image: String,
    val title: String
) {
    companion object {
        fun create(
            categoryHierarchy: String,
            image: String,
            title: String
        ): EitherNel<Nothing, YourLibraryItemDomainModel> {
            return YourLibraryItemDomainModel(
                categoryHierarchy = categoryHierarchy,
                image = image,
                title = title
            ).right()
        }
    }
}
