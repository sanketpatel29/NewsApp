package com.sanket.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.sanket.newsapp.data.local.entity.Source


data class ApiSource(

    @SerializedName("id")
    var id: String = "",

    @SerializedName("name")
    var name: String = "",

    )

fun ApiSource.toSource(): Source {
    return Source(
        id = id,
        name = name,
        /* country = country,
         description = description,
         language = language,
         category = category,
         url = url*/
    )
}