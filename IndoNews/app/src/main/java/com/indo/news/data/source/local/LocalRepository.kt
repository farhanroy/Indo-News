package com.indo.news.data.source.local

import com.indo.news.services.db.dao.FavoriteDao
import com.indo.news.services.db.entity.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(private val favoriteDao: FavoriteDao) {
    suspend fun insertFavorite(data: Favorite) {
        favoriteDao.insertFavorite(data)
    }

    suspend fun deleteFavorite(title: String) {
        favoriteDao.deleteFavorite(title)
    }

    fun getAllFavorite(): Flow<List<Favorite>> {
        return favoriteDao.getAllFavorite()
    }

    fun getFavoriteByTitle(title: String): Flow<Favorite> {
        return favoriteDao.getFavoriteByTitle(title)
    }

    suspend fun isFavorite(title: String): Int = favoriteDao.isFavorite(title)
}