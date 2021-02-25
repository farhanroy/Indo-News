package com.indo.news.services.db.dao

import androidx.room.*
import com.indo.news.data.model.Article
import com.indo.news.services.db.entity.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao  {
    @Query("SELECT * FROM favorite_table")
    fun getAllFavorite(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite_table WHERE title LIKE :title")
    fun getFavoriteByTitle(title: String): Flow<Favorite>

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_table WHERE title=:title)")
    suspend fun isFavorite(title: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(data: Favorite)

    @Query("DELETE FROM favorite_table WHERE title = :title")
    suspend fun deleteFavorite(title: String)
}