package com.indo.news.modules.business.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.indo.news.data.source.remote.NewsRepository
import com.indo.news.utils.Result

class BusinessVM  @ViewModelInject constructor(
    private val repository: NewsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val getBusinessNews = liveData {
        try {
            emit(Result.InProgress)
            val result = repository.getBusinessNews()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}