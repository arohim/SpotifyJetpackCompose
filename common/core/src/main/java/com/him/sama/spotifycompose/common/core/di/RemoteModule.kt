package com.him.sama.spotifycompose.common.core.di

import android.content.Context
import co.infinum.retromock.Retromock
import co.infinum.retromock.create
import com.google.gson.Gson
import com.him.sama.spotifycompose.common.core.BuildConfig
import com.him.sama.spotifycompose.common.core.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class BaseUrl

@Module
@InstallIn(SingletonComponent::class)
internal class RemoteModule {

    @Provides
    @Singleton
    fun gson(): Gson = Gson()

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

    @Provides
    @Singleton
    fun retrofit(
        @BaseUrl baseUrl: String,
        gson: Gson,
        client: OkHttpClient,
    ): Retrofit =
        provideRetrofit(
            baseUrl = baseUrl,
            gson = gson,
            client = client,
        )

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient = provideOkHttpClient()

    @Provides
    @BaseUrl
    fun baseUrl(): String = "https://test.google.com/"
}


private fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(baseUrl)
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    level = if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                }
        )
        .build()
}
