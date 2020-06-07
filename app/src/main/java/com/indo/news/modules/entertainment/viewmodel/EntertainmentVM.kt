package com.indo.news.modules.entertainment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.indo.news.data.source.NewsRepository
import com.indo.news.utils.Result
import javax.inject.Inject

class EntertainmentVM @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    val getEntertainmentNews = liveData {
        try {
            emit(Result.InProgress)
            val result = repository.getEntertainmentNews()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}