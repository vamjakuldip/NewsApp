package com.kuldip.data.model

import java.util.*

data class NewsArticleEntity(
    val sourceId: String, val title: String, val description: String, val author: String?, val source: String,
    val publishedAt: Date, val url: String, val urlToImage: String
)