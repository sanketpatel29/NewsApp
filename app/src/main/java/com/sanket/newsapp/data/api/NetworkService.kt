package com.sanket.newsapp.data.api

import com.sanket.newsapp.data.model.NewsSourcesResponse
import com.sanket.newsapp.data.model.TopHeadLineResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): TopHeadLineResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @GET("top-headlines")
    suspend fun getNewsByCountry(@Query("country") country: String): TopHeadLineResponse

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") source: String): TopHeadLineResponse

    @GET("top-headlines")
    suspend fun getNewsByLanguages(@Query("language") source: String): TopHeadLineResponse

    @GET("everything")
    suspend fun getNewsBySearch(@Query("q") phrase: String): TopHeadLineResponse
}