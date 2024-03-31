package com.him.sama.spotifycompose.common.core.di

import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import com.him.sama.spotifycompose.common.core.data.repository.HomeRepositoryImpl
import com.him.sama.spotifycompose.common.core.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideApiService(apiService: ApiService): HomeRepository =
        HomeRepositoryImpl(apiService = apiService)

}