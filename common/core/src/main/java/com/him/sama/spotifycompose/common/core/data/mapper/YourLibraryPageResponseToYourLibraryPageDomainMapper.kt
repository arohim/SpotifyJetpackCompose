package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.EitherNel
import arrow.core.getOrElse
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.data.remote.model.YourLibraryResponse
import com.him.sama.spotifycompose.common.core.domain.model.RecentItemDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel
import javax.inject.Inject

class YourLibraryPageResponseToYourLibraryPageDomainMapper @Inject constructor() :
    Mapper<YourLibraryResponse, EitherNel<Nothing, YourLibraryDomainModel>> {

    override fun invoke(param: YourLibraryResponse): EitherNel<Nothing, YourLibraryDomainModel> {
        return YourLibraryDomainModel.create(
            recent = param.recent.map {
                RecentItemDomainModel.create(
                    categoryHierarchy = it.categoryHierarchy,
                    image = it.image,
                    title = it.title
                ).getOrElse { error("") }
            }
        )
    }

}
