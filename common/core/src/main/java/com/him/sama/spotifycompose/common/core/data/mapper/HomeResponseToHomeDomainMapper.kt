package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.Nel
import arrow.core.Validated
import com.him.sama.spotifycompose.common.core.di.HomeResponseToHomeDomainMapperType
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeLayoutType
import com.him.sama.spotifycompose.common.core.domain.model.HomeModelItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeValidationError
import javax.inject.Inject

internal class HomeResponseToHomeDomainMapper @Inject constructor() :
    HomeResponseToHomeDomainMapperType {
    override fun invoke(param: HomeResponseItem): Validated<Nel<HomeValidationError>, HomeModelItem> {
        return HomeModelItem.create(
            layoutType = HomeLayoutType.valueOf(param.layoutType.name),
            title = param.title,
            items = listOf()
        )
    }
}
