package com.kuldip.data.repository

import com.kuldip.data.model.CreateNewsRequestEntity
import com.kuldip.data.model.NewsArticleEntity
import io.reactivex.rxjava3.core.Single

interface NewsArticleRemoteDataStore {

    fun getNewsArticles(createNewsRequest: CreateNewsRequestEntity): Single<List<NewsArticleEntity>>

}