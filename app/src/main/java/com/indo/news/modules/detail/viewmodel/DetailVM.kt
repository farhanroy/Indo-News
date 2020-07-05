package com.indo.news.modules.detail.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.indo.news.data.source.remote.RemoteRepository
import com.indo.news.utils.Result

class DetailVM @ViewModelInject constructor(
    private val repository: RemoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val getRecommendedNews = liveData {
        try {
            emit(Result.InProgress)
            val data = repository.getRecommendedNews()
            emit(Result.Success(data))
        } catch (e: Exception){
            emit(Result.Error(e))
        }
    }
}