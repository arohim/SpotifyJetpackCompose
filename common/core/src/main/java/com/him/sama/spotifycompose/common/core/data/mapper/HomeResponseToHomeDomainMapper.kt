package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.getOrElse
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainDetailItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainLayoutType
import javax.inject.Inject

class HomeResponseToHomeDomainMapper @Inject constructor() :
    Mapper<HomeResponseItem, Either<NonEmptyList<Void>, HomeDomainItem>> {

    override fun invoke(param: HomeResponseItem): Either<NonEmptyList<Void>, HomeDomainItem> {
        return HomeDomainItem.create(
            layoutType = HomeDomainLayoutType.valueOf(param.layoutType.name),
            title = param.title,
            image = param.image,
            items = param.items.map {
                HomeDomainDetailItem.create(
                    image = it.image ?: "",
                    title = it.title,
                    description = it.description,
                    categoryHierarchy = it.categoryHierarchy ?: "",
                    categoryName = it.categoryName ?: ""
                ).getOrElse { error("") }
            }
        )
    }
}
