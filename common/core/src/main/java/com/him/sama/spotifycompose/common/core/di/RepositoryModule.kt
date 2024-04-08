package com.him.sama.spotifycompose.common.core.di

import com.him.sama.spotifycompose.common.core.core.Mapper
import com.him.sama.spotifycompose.common.core.data.mapper.NetworkErrorMapper
import com.him.sama.spotifycompose.common.core.data.repository.HomeRepositoryImpl
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Binds
    @Singleton
    fun provideApiService(impl: HomeRepositoryImpl): HomeRepository = impl

}