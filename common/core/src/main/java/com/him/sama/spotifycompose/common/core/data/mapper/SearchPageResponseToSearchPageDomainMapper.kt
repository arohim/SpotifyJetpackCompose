package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.EitherNel
import arrow.core.getOrElse
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.data.remote.model.SearchPageResponse
import com.him.sama.spotifycompose.common.core.domain.model.CategoryDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.SearchPageDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.StoryItemDomainModel
import javax.inject.Inject

class SearchPageResponseToSearchPageDomainMapper @Inject constructor() :
    Mapper<SearchPageResponse, EitherNel<Nothing, SearchPageDomainModel>> {

    override fun invoke(param: SearchPageResponse): EitherNel<Nothing, SearchPageDomainModel> {
        return SearchPageDomainModel.create(
            categories = param.categories.map {
                CategoryDomainModel.create(
                    bgColor = it.bgColor,
                    title = it.title,
                    image = it.image
                ).getOrElse { error("") }
            },
            story = param.story.map {
                StoryItemDomainModel.create(
                    title = it.title,
                    image = it.image
                ).getOrElse { error("") }
            }
        )
    }

}
