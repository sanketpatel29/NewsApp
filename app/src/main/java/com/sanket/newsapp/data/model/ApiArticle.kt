package com.sanket.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.sanket.newsapp.data.local.entity.Article


data class ApiArticle(

    @SerializedName("source")
    var apiSource: ApiSource,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("description")
    var description: String = "",

    @SerializedName("url")
    var url: String = "",

    @SerializedName("urlToImage")
    var imageUrl: String = "",
)

fun ApiArticle.toArticle(): Article {
    return Article(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        source = apiSource.toSource()
    )
}

public fun List<ApiArticle>.toArticleList(): List<Article> {
    return map { it.toArticle() }
}