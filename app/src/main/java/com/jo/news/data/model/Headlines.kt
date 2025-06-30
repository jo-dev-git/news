package com.jo.news.data.model

data class Headlines(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article>?
)
