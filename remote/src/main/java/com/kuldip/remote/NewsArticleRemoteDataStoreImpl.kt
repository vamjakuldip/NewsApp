package com.kuldip.remote

import com.kuldip.data.model.CreateNewsRequestEntity
import com.kuldip.data.model.NewsArticleEntity
import com.kuldip.data.repository.NewsArticleRemoteDataStore
import com.kuldip.remote.api.Repository
import com.kuldip.remote.mapper.NewsArticleModelMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsArticleRemoteDataStoreImpl @Inject constructor(
    private val httpClient: Repository,
    private val mapper: NewsArticleModelMapper
) :
    NewsArticleRemoteDataStore {

    companion object {
        private const val NEWS_API_KEY = "02e04c82496a4e489f0035e2db43fb9a"
    }

    // NewsArticleRemote Methods.
    override fun getNewsArticles(createNewsRequest: CreateNewsRequestEntity): Single<List<NewsArticleEntity>> {
        return httpClient.getNewsArticles(apiKey = NEWS_API_KEY, createNewsRequest).map { it.articles.map { mapper.mapFromModel(it) } }
    }
}