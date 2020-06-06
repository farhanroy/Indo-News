package com.indo.news.data.source

import com.indo.news.data.network.NewsService
import com.indo.news.data.model.News
import com.indo.news.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val service: NewsService
) {
    suspend fun getHeadlineNews(): News = service.getHeadlineNews(Constants.API_KEY)
}