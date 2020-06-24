package com.indo.news.services.network

import com.indo.news.data.model.News
import com.indo.news.utils.constant.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines?country=id")
    suspend fun getHomeNews(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("pageSize") pageSize: Int = Constants.PAGE_SIZE,
        @Query("page") page: Int): News

    @GET("top-headlines?country=id&category=entertainment")
    suspend fun getEntertainmentNews(@Query("apiKey") apiKey: String): News

    @GET("top-headlines?country=id&category=business")
    suspend fun getBusinessNews(@Query("apiKey") apiKey: String): News

    @GET("top-headlines?country=id&category=sports")
    suspend fun getSportNews(@Query("apiKey") apiKey: String): News
}