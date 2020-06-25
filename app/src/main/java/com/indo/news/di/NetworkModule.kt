package com.indo.news.di

import android.app.Application
import android.content.Context
import com.indo.news.services.network.NewsService
import com.indo.news.services.network.interceptors.CacheInterceptor
import com.indo.news.services.network.interceptors.ErrorInterceptor
import com.indo.news.services.network.interceptors.ForceCacheInterceptor
import com.indo.news.utils.constant.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(@ApplicationContext appContext: Context): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .cache(Cache(appContext.cacheDir, Constants.CACHE_SIZE))
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC))
            .addInterceptor(ErrorInterceptor())
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(ForceCacheInterceptor(appContext))
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