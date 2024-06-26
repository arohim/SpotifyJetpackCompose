package com.him.sama.spotifycompose.common.core.data.mapper

import arrow.core.Either
import arrow.core.EitherNel
import arrow.core.NonEmptyList
import arrow.core.getOrElse
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.data.remote.model.YourLibraryResponse
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryItemDomainModel
import javax.inject.Inject

class YourLibraryPageResponseToYourLibraryPageDomainMapper @Inject constructor() :
    Mapper<YourLibraryResponse, Either<NonEmptyList<Void>, YourLibraryDomainModel>> {

    override fun invoke(param: YourLibraryResponse): EitherNel<Nothing, YourLibraryDomainModel> {
        return YourLibraryDomainModel.create(
            title = param.title,
            items = param.items.map {
                YourLibraryItemDomainModel.create(
                    categoryHierarchy = it.categoryHierarchy,
                    image = it.image,
                    title = it.title
                ).getOrElse { error("") }
            }
        )
    }

}
