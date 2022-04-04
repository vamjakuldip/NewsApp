package com.kuldip.data.repository

import com.kuldip.data.model.CreateNewsRequestEntity
import com.kuldip.data.model.NewsArticleEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface NewsArticleCacheDataStore {

    fun clearNewsArticles(): Completable

    fun saveNewsArticles(newsArticles: List<NewsArticleEntity>): Completable

    fun getNewsArticles(createNewsRequestEntity: CreateNewsRequestEntity): Single<List<NewsArticleEntity>>

    fun areNewsArticlesCached(): Single<Boolean>

}