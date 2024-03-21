package com.sanket.newsapp.data.local

import com.sanket.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    fun getNewsArticles(): Flow<List<Article>>
    fun deleteAndInsertNewsArticles(articles: List<Article>)

}