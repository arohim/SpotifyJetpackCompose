package com.him.sama.spotifycompose.di

import com.him.sama.spotifycompose.common.core.core.dispatcher.AppCoroutineDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CoreModule {
  @Binds
  @Singleton
  abstract fun coroutineDispatchers(impl: DefaultCoroutineDispatchers): AppCoroutineDispatchers
}
