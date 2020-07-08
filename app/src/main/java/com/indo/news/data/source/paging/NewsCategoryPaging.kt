package com.indo.news.data.source.paging

import androidx.paging.PagingSource
import com.indo.news.data.model.Article
import com.indo.news.services.network.NewsService
import com.indo.news.utils.constant.Constants
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Singleton

@Singleton
class NewsCategoryPaging(
    private val service: NewsService,
    private val category: String
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: Constants.PAGE_KEY
        return try {
            val response =
                service.getNewsByCategory(category = category, page = position, pageSize = 20)
            val item = response.articles
            LoadResult.Page(
                data = item,
                prevKey = if (position == Constants.PAGE_KEY) null else position - 1,
                nextKey = if (item.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            Timber.e(exception)
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Timber.e(exception)
            LoadResult.Error(exception)
        }
    }
}