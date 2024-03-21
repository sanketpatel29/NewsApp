package com.sanket.newsapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Source(

    @PrimaryKey
    @ColumnInfo("source_id")
    var id: String?,

    @ColumnInfo("source_name")
    var name: String?,
    )