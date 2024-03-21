package com.sanket.newsapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsArticle")
data class Article(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") val url: String = "",
    @ColumnInfo(name = "imageUrl") val imageUrl: String? = "",
    @ColumnInfo(name = "language") val language: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @Embedded val source: Source
)