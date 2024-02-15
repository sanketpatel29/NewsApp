package com.sanket.newsapp.data.model

import com.google.gson.annotations.SerializedName


data class Source(

    @SerializedName("id")
    var id: String = "",

    @SerializedName("name")
    var name: String = "",

    @SerializedName("category")
    var category: String,

    @SerializedName("country")
    var country: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("language")
    var language: String,

    @SerializedName("url")
    var url: String

)