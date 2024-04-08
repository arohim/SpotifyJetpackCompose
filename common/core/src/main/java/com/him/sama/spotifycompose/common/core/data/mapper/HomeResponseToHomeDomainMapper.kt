package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.EitherNel
import com.him.sama.spotifycompose.common.core.core.Mapper
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeLayoutType
import com.him.sama.spotifycompose.common.core.domain.model.HomeModelItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeValidationError
import javax.inject.Inject

internal class HomeResponseToHomeDomainMapper @Inject constructor() :
    Mapper<HomeResponseItem, EitherNel<HomeValidationError, HomeModelItem>> {
    override fun invoke(param: HomeResponseItem): EitherNel<HomeValidationError, HomeModelItem> {
        return HomeModelItem.create(
            layoutType = HomeLayoutType.valueOf(param.layoutType.name),
            title = param.title,
            items = listOf()
        )
    }
}
