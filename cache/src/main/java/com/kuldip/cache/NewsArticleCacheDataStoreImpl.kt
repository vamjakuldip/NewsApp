package com.kuldip.cache

import android.util.Log
import com.kuldip.cache.db.NewsArticlesDatabase
import com.kuldip.cache.mapper.CachedNewsArticleMapper
import com.kuldip.data.model.CreateNewsRequestEntity
import com.kuldip.data.model.NewsArticleEntity
import com.kuldip.data.repository.NewsArticleCacheDataStore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsArticleCacheDataStoreImpl @Inject constructor(
    private val newsArticlesDatabase: NewsArticlesDatabase,
    private val mapper: CachedNewsArticleMapper
) : NewsArticleCacheDataStore {

    // NewsArticleCache Methods.
    override fun clearNewsArticles(): Completable {
        return newsArticlesDatabase.cachedNewsArticlesDao().deleteNewsArticles()
    }

    override fun saveNewsArticles(newsArticles: List<NewsArticleEntity>): Completable {
        return newsArticlesDatabase.cachedNewsArticlesDao().insertNewsArticles(
            newsArticles.map { mapper.mapToCached(it) }
        )
    }

    override fun getNewsArticles(createNewsRequestEntity: CreateNewsRequestEntity): Single<List<NewsArticleEntity>> {
        Log.d("getNewsArticles", "createNewsRequestEntity: ${createNewsRequestEntity.pageSize} offset:${createNewsRequestEntity.pageSize * (createNewsRequestEntity.page - 1)}")
        return newsArticlesDatabase.cachedNewsArticlesDao().getNewsArticles(pageSize = createNewsRequestEntity.pageSize, offset = createNewsRequestEntity.pageSize * (createNewsRequestEntity.page - 1))
            .map { it.map { mapper.mapFromCached(it) } }
    }

    override fun areNewsArticlesCached(): Single<Boolean> {
        return newsArticlesDatabase.cachedNewsArticlesDao().getNewsArticlesCount().map { it > 0 }
    }
}