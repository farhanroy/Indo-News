package com.indo.news.modules.sport.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.indo.news.data.source.remote.NewsRepository
import com.indo.news.utils.Result

class SportVM  @ViewModelInject constructor(
    private val repository: NewsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val getSportNews = liveData {
        try {
            emit(Result.InProgress)
            val result = repository.getSportNews()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}