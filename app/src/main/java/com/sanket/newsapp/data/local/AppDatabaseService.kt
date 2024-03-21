package com.sanket.newsapp.data.local

import com.sanket.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

class AppDatabaseService constructor(private val appDatabase: AppDatabase) : DatabaseService {
    override fun getNewsArticles(): Flow<List<Article>> {
        return appDatabase.newsArticlesDao().getNewsArticles()
    }

    override fun deleteAndInsertNewsArticles(articles: List<Article>) {
        appDatabase.newsArticlesDao().deleteAndInsertAllNewsArticles(articles)
    }

}