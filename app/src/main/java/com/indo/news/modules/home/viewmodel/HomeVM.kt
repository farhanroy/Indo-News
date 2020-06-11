package com.indo.news.modules.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.indo.news.data.model.Article
import com.indo.news.data.source.remote.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeVM @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    private var currentArticleResult: Flow<PagingData<Article>>? = null

    fun getHomeNews(): Flow<PagingData<Article>> {
        val lastResult = currentArticleResult
        if (lastResult != null) return lastResult
        val result: Flow<PagingData<Article>> = repository.getHomeNews().cachedIn(viewModelScope)
        currentArticleResult = result
        return result
    }
}