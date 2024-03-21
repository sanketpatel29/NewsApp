package com.sanket.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sanket.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticlesDao {
    @Transaction
    @Query("SELECT * FROM NewsArticle")
    fun getNewsArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsArticles(articles: List<Article>): List<Long>

    @Query("DELETE FROM NewsArticle")
    fun deleteNewsArticles()

    @Transaction
    fun deleteAndInsertAllNewsArticles(
        articles: List<Article>
    ): List<Long> {
        deleteNewsArticles()
        return insertNewsArticles(articles)
    }


}