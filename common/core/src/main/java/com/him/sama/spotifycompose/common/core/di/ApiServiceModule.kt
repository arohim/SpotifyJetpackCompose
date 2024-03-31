package com.him.sama.spotifycompose.common.core.di

import android.content.Context
import co.infinum.retromock.Retromock
import co.infinum.retromock.create
import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Singleton
    fun providerRetromock(
        context: Context,
        retrofit: Retrofit
    ): Retromock = Retromock.Builder()
        .retrofit(retrofit)
        .defaultBodyFactory(context.assets::open)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retromock: Retromock): ApiService = retromock.create()

}