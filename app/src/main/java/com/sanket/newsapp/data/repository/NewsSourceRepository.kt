package com.sanket.newsapp.data.repository

import com.sanket.newsapp.data.api.NetworkService
import com.sanket.newsapp.data.model.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourceRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsSources(): Flow<List<Source>> {
        return flow {
            emit(networkService.getNewsSources())
        }.map {
            it.sources
        }
    }
}

