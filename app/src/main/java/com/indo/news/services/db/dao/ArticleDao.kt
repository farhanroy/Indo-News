package com.indo.news.services.db.dao

import androidx.room.*
import com.indo.news.services.db.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao  {
    @Query("SELECT * FROM article_table")
    fun getAllArticle(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(data: Article)

    @Delete
    suspend fun deleteArticle(data: Article)
}