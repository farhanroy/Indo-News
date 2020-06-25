package com.indo.news.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.indo.news.data.model.News
import com.indo.news.data.source.NewsDataSource
import com.indo.news.services.db.entity.Article
import com.indo.news.services.network.NewsService
import com.indo.news.utils.constant.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val service: NewsService
) {
    fun getNewsByCategory(category: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = { NewsDataSource(service, category) }
        ).flow
    }
}