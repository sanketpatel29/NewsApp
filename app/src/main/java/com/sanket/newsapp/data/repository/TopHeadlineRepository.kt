package com.sanket.newsapp.data.repository

import com.sanket.newsapp.data.api.NetworkService
import com.sanket.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }

    fun getNewsByCountry(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsByCountry(country))
        }.map {
            it.articles
        }
    }

    fun getNewsBySources(source: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsBySources(source))
        }.map {
            it.articles
        }
    }

    fun getNewsByLanguages(language: String): Flow<ArrayList<Article>> {
        return flow {
            emit(networkService.getNewsByLanguages(language))
        }.map { it.articles }
    }

    fun getNewsBySearch(phrase: String): Flow<ArrayList<Article>> {
        return flow {
            emit(networkService.getNewsBySearch(phrase))
        }.map { it.articles }
    }
}