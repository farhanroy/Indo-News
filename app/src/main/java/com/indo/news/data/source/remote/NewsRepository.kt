package com.indo.news.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.indo.news.data.model.Article
import com.indo.news.data.model.News
import com.indo.news.data.network.NewsService
import com.indo.news.data.source.NewsDataSource
import com.indo.news.utils.constant.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val service: NewsService
) {
    fun getHomeNews(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = { NewsDataSource(service) }
        ).flow
    }


    suspend fun getEntertainmentNews(): News = service.getEntertainmentNews(Constants.API_KEY)

    suspend fun getBusinessNews(): News = service.getBusinessNews(Constants.API_KEY)

    suspend fun getSportNews(): News = service.getSportNews(Constants.API_KEY)
}