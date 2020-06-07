package com.indo.news.modules.sport.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.indo.news.data.source.NewsRepository
import com.indo.news.utils.Result
import javax.inject.Inject

class SportVM  @Inject constructor(
    private val repository: NewsRepository
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