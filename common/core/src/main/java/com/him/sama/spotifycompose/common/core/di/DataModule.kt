package com.him.sama.spotifycompose.common.core.di

import arrow.core.Either
import arrow.core.NonEmptyList
import com.him.sama.spotifycompose.common.core.base.Mapper
import com.him.sama.spotifycompose.common.core.data.mapper.HomeResponseToHomeDomainMapper
import com.him.sama.spotifycompose.common.core.data.mapper.NetworkErrorMapper
import com.him.sama.spotifycompose.common.core.data.mapper.SearchPageResponseToSearchPageDomainMapper
import com.him.sama.spotifycompose.common.core.data.mapper.YourLibraryPageResponseToYourLibraryPageDomainMapper
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.data.remote.model.SearchPageResponse
import com.him.sama.spotifycompose.common.core.data.remote.model.YourLibraryResponse
import com.him.sama.spotifycompose.common.core.data.repository.HomeRepositoryImpl
import com.him.sama.spotifycompose.common.core.data.repository.SearchRepositoryImpl
import com.him.sama.spotifycompose.common.core.data.repository.YourLibraryRepositoryImpl
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.SearchPageDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
import com.him.sama.spotifycompose.common.core.domain.repository.SearchRepository
import com.him.sama.spotifycompose.common.core.domain.repository.YourLibraryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    @Singleton
    abstract fun homeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun searchRepository(impl: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun yourLibraryRepository(impl: YourLibraryRepositoryImpl): YourLibraryRepository

    @Binds
    @Singleton
    abstract fun homeModelMapper(impl: HomeResponseToHomeDomainMapper): Mapper<HomeResponseItem, Either<NonEmptyList<Void>, HomeDomainItem>>

    @Binds
    @Singleton
    abstract fun searchPageModelMapper(impl: SearchPageResponseToSearchPageDomainMapper): Mapper<SearchPageResponse, Either<NonEmptyList<Void>, SearchPageDomainModel>>

    @Binds
    @Singleton
    abstract fun yourLibraryModelMapper(impl: YourLibraryPageResponseToYourLibraryPageDomainMapper): Mapper<YourLibraryResponse, Either<NonEmptyList<Void>, YourLibraryDomainModel>>

    @Binds
    abstract fun userErrorMapper(impl: NetworkErrorMapper): Mapper<Throwable, UserError>

}