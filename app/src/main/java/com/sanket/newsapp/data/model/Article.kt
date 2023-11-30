package com.sanket.newsapp.data.model

import com.google.gson.annotations.SerializedName


data class Article(

    @SerializedName("source")
    var source: Source,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("description")
    var description: String = "",

    @SerializedName("url")
    var url: String = "",

    @SerializedName("urlToImage")
    var imageUrl: String = ""
)