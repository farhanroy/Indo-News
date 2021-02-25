package com.indo.news.modules.detail.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.indo.news.data.model.Article
import com.indo.news.data.source.local.LocalRepository
import com.indo.news.data.source.remote.RemoteRepository
import com.indo.news.utils.Result
import com.indo.news.utils.extension.articleToFavorite
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailVM @ViewModelInject constructor(
    private val remote: RemoteRepository,
    private val local: LocalRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val getRecommendedNews = liveData {
        try {
            emit(Result.InProgress)
            val data = remote.getRecommendedNews()
            emit(Result.Success(data))
        } catch (e: Exception){
            emit(Result.Error(e))
        }
    }

    fun getFavoriteByTitle(title: String) = local.getFavoriteByTitle(title).asLiveData()


    fun insertFavorite(article: Article) = viewModelScope.launch {
        local.insertFavorite(articleToFavorite(article))
    }

    fun deleteFavorite(article: Article) = viewModelScope.launch {
        try {
            local.deleteFavorite(article.title)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    suspend fun isFavorite(title: String): Int = local.isFavorite(title)

}