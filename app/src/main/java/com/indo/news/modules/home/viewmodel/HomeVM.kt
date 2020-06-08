package com.indo.news.modules.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.indo.news.data.source.NewsRepository
import com.indo.news.utils.Result
import javax.inject.Inject

class HomeVM @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    val getHomeNews = liveData {
        try {
            emit(Result.InProgress)
            val result = repository.getHomeNews()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

}