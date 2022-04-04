package com.kuldip.domain.repository

import com.kuldip.domain.model.CreateNewsRequest
import com.kuldip.domain.model.NewsArticle
import io.reactivex.rxjava3.core.Single

interface NewsArticlesRepository {

    fun getNewsArticles(createNewsRequest: CreateNewsRequest): Single<List<NewsArticle>>

}