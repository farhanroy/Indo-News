package com.indo.news.data.source

import androidx.paging.PagingSource
import com.indo.news.data.model.Article
import com.indo.news.data.network.NewsService
import com.indo.news.utils.constant.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Singleton

@Singleton
class NewsDataSource(private val service: NewsService) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: Constants.PAGE_KEY
        return try {
            val response = service.getHomeNews(page = position, pageSize = 20)
            val item = response.articles
            LoadResult.Page(
                data = item,
                prevKey = if (position == Constants.PAGE_KEY) null else position - 1,
                nextKey = if (item.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}