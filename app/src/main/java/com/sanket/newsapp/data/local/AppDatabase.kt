package com.sanket.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanket.newsapp.data.local.dao.NewsArticlesDao
import com.sanket.newsapp.data.local.entity.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsArticlesDao(): NewsArticlesDao

}