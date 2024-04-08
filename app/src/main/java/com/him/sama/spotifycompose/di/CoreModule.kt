package com.him.sama.spotifycompose.di

import android.app.Application
import android.content.Context
import com.him.sama.spotifycompose.common.core.core.dispatcher.AppCoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class CoreModule {

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun coroutineDispatchers(impl: DefaultCoroutineDispatchers): AppCoroutineDispatchers = impl
}
