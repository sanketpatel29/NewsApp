package com.sanket.newsapp.data.api

import com.sanket.newsapp.AppUtils.Constants.API_KEY
import com.sanket.newsapp.data.model.TopHeadLineResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadLineResponse
}