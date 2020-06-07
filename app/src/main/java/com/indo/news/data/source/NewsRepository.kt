package com.indo.news.data.source

import com.indo.news.data.network.NewsService
import com.indo.news.data.model.News
import com.indo.news.utils.constant.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val service: NewsService
) {
    suspend fun getHomeNews(): News = service.getHomeNews(Constants.API_KEY)

    suspend fun getEntertainmentNews(): News = service.getEntertainmentNews(Constants.API_KEY)

    suspend fun getBusinessNews(): News = service.getBusinessNews(Constants.API_KEY)

    suspend fun getSportNews(): News = service.getSportNews(Constants.API_KEY)
}