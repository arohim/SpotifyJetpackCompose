package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.EitherNel
import arrow.core.getOrElse
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainDetailItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainLayoutType
import javax.inject.Inject

internal class HomeResponseToHomeDomainMapper @Inject constructor() :
    Mapper<HomeResponseItem, EitherNel<Nothing, HomeDomainItem>> {

    override fun invoke(param: HomeResponseItem): EitherNel<Nothing, HomeDomainItem> {
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
