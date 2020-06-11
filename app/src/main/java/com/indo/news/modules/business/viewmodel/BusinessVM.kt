package com.indo.news.modules.business.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.indo.news.data.source.remote.NewsRepository
import com.indo.news.utils.Result
import javax.inject.Inject

class BusinessVM  @Inject constructor(
    private val repository: NewsRepository
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