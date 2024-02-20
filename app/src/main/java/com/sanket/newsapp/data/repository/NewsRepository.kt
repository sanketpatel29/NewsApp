package com.sanket.newsapp.data.repository

import com.sanket.newsapp.data.api.NetworkService
import com.sanket.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsByCountry(countryCode: String): Flow<ArrayList<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(countryCode))
        }.map { it.articles }
    }

    fun getNewsBySources(sourceId: String): Flow<ArrayList<Article>> {
        return flow {
            emit(networkService.getNewsBySources(sourceId))
        }.map { it.articles }
    }
}