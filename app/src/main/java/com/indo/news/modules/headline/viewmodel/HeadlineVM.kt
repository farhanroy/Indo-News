package com.indo.news.modules.headline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.indo.news.data.model.News
import com.indo.news.data.source.NewsRepository
import com.indo.news.utils.debug
import javax.inject.Inject

class HeadlineVM @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    val getHeadlineNews = liveData {
        try {
            val result = repository.getHeadlineNews()
            emit(result)
        } catch (e: Exception) {
            e.message?.let { debug(it) }
        }
    }
}