package com.him.sama.spotifycompose.common.core.di

import arrow.core.EitherNel
import arrow.core.Nel
import arrow.core.Validated
import com.him.sama.spotifycompose.common.core.core.Mapper
import com.him.sama.spotifycompose.common.core.data.mapper.HomeResponseToHomeDomainMapper
import com.him.sama.spotifycompose.common.core.data.mapper.NetworkErrorMapper
import com.him.sama.spotifycompose.common.core.data.remote.model.HomeResponseItem
import com.him.sama.spotifycompose.common.core.data.repository.HomeRepositoryImpl
import com.him.sama.spotifycompose.common.core.domain.model.HomeModelItem
import com.him.sama.spotifycompose.common.core.domain.model.HomeValidationError
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
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
    abstract fun homeResponseToHomeModelMapper(impl: HomeResponseToHomeDomainMapper): Mapper<HomeResponseItem, EitherNel<HomeValidationError, HomeModelItem>>

    @Binds
    abstract fun userErrorMapper(impl: NetworkErrorMapper): Mapper<Throwable, UserError>

}