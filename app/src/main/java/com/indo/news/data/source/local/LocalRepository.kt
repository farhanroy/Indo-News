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

    suspend fun deleteFavorite(data: Favorite) {
        favoriteDao.deleteFavorite(data)
    }

    fun getAllFavorite(): Flow<List<Favorite>> {
        return favoriteDao.getAllFavorite()
    }

    fun getFavoriteById(id: Long): Flow<Favorite> {
        return favoriteDao.getFavoriteById(id)
    }
}