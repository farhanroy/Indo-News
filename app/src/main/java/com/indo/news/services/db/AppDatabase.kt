package com.indo.news.services.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indo.news.services.db.dao.FavoriteDao
import com.indo.news.services.db.entity.Favorite
import com.indo.news.utils.constant.Constants

@Database(entities = [Favorite::class], version = Constants.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}