package com.indo.news.modules.favorite.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.indo.news.data.source.local.LocalRepository

class FavoriteVM @ViewModelInject constructor(
    private val repository: LocalRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val getAllFavorite = repository.getAllFavorite().asLiveData()
}