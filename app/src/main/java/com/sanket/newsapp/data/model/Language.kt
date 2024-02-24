package com.sanket.newsapp.data.model

data class Language(
    var name: String,
    var code: String,

    @Transient
    var isChecked: Boolean
)
