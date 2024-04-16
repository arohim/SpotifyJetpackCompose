package com.him.sama.spotifycompose.common.core.domain.model

import arrow.core.EitherNel
import arrow.core.right

data class YourLibraryDomainModel(
    val recent: List<RecentItemDomainModel>
) {
    companion object {
        fun create(recent: List<RecentItemDomainModel>): EitherNel<Nothing, YourLibraryDomainModel> {
            return YourLibraryDomainModel(
                recent = recent
            ).right()
        }
    }
}

data class RecentItemDomainModel(
    val categoryHierarchy: String,
    val image: String,
    val title: String
) {
    companion object {
        fun create(
            categoryHierarchy: String,
            image: String,
            title: String
        ): EitherNel<Nothing, RecentItemDomainModel> {
            return RecentItemDomainModel(
                categoryHierarchy = categoryHierarchy,
                image = image,
                title = title
            ).right()
        }
    }
}
