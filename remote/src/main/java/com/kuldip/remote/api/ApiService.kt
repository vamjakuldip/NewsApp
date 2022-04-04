package com.kuldip.remote.api

import com.kuldip.remote.model.NewsArticleResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("top-headlines")
    fun getNewsArticles(@Query("apiKey", encoded = true) apiKey: String?, @Query("pageSize", encoded = true) pageSize: Int = 10, @Query("page", encoded = true) page: Int = 1, @Query("country", encoded = true) country: String = "IN"): Single<NewsArticleResponseModel>
}
