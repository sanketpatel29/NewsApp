package com.sanket.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsSourcesResponse(

    @SerializedName("sources")
    var sources: List<Source> = arrayListOf(),

    @SerializedName("status")
    var status: String = ""
)