package com.indo.news.modules.home.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.indo.news.data.source.remote.NewsRepository
import com.indo.news.services.db.entity.Article
import kotlinx.coroutines.flow.Flow

class HomeVM @ViewModelInject constructor(
    private val repository: NewsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
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