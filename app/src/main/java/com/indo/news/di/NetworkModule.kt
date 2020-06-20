package com.indo.news.di

import android.app.Application
import com.indo.news.data.network.NewsService
import com.indo.news.data.network.interceptors.CacheInterceptor
import com.indo.news.data.network.interceptors.ErrorInterceptor
import com.indo.news.data.network.interceptors.ForceCacheInterceptor
import com.indo.news.utils.constant.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(application: Application): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .cache(Cache(application.cacheDir, Constants.CACHE_SIZE))
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC))
            .addInterceptor(ErrorInterceptor())
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(ForceCacheInterceptor(application))
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(application: Application): NewsService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(provideOkHttp(application))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }
}