package com.sanket.newsapp.data.api

import com.sanket.newsapp.data.model.NewsSourcesResponse
import com.sanket.newsapp.data.model.TopHeadLineResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    //    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadLineResponse

    //    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    //    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getNewsByCountry(@Query("country") country: String): TopHeadLineResponse

    //    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") source: String): TopHeadLineResponse

    //    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getNewsByLanguages(@Query("language") source: String): TopHeadLineResponse

    //    @Headers("X-Api-Key: $API_KEY")
    @GET("everything")
    suspend fun getNewsBySearch(@Query("q") phrase: String): TopHeadLineResponse
}