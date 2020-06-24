package com.indo.news.services.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indo.news.services.db.dao.ArticleDao
import com.indo.news.services.db.entity.Article
import com.indo.news.utils.constant.Constants

@Database(entities = [Article::class], version = Constants.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}