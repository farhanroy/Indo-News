package com.indo.news.services.db.dao

import androidx.room.*
import com.indo.news.services.db.entity.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao  {
    @Query("SELECT * FROM favorite_table")
    fun getAllFavorite(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite_table WHERE id = :id")
    fun getFavoriteById(id: Long): Flow<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(data: Favorite)

    @Delete
    suspend fun deleteFavorite(data: Favorite)
}