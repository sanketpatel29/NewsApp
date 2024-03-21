package com.sanket.newsapp.data.repository

import com.sanket.newsapp.data.api.NetworkService
import com.sanket.newsapp.data.local.DatabaseService
import com.sanket.newsapp.data.local.entity.Article
import com.sanket.newsapp.data.model.ApiArticle
import com.sanket.newsapp.data.model.toArticle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun getTopHeadlines(country: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.apiArticles
        }
    }

    fun getNewsByCountry(country: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getNewsByCountry(country))
        }.map {
            it.apiArticles
        }
    }

    fun getNewsBySources(source: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getNewsBySources(source))
        }.map {
            it.apiArticles
        }
    }

    fun getNewsByLanguages(language: String): Flow<ArrayList<ApiArticle>> {
        return flow {
            emit(networkService.getNewsByLanguages(language))
        }.map { it.apiArticles }
    }

    fun getNewsBySearch(phrase: String): Flow<ArrayList<ApiArticle>> {
        return flow {
            emit(networkService.getNewsBySearch(phrase))
        }.map { it.apiArticles }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getNewsArticles(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map { it ->
            it.apiArticles.map { apiArticle -> apiArticle.toArticle() }
        }.flatMapConcat { articles ->
            flow {
                emit(databaseService.deleteAndInsertNewsArticles(articles))
            }
        }.flatMapConcat { databaseService.getNewsArticles() }
    }

    fun getNewsArticlesDirectFromDb(country: String): Flow<List<Article>> {
        return databaseService.getNewsArticles()
    }
}