package com.sanket.newsapp.data.model

import com.google.gson.annotations.SerializedName


data class TopHeadLineResponse(

    @SerializedName("status")
    var status: String = "",

    @SerializedName("totalResults")
    var totalResults: Int = 0,

    @SerializedName("articles")
    var apiArticles: ArrayList<ApiArticle> = arrayListOf()

)