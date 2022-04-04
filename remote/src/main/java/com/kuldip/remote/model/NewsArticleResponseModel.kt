package com.kuldip.remote.model

import com.google.gson.annotations.SerializedName

data class NewsArticleResponseModel(
    @SerializedName("articles")
    val articles: List<NewsArticleModel>
)