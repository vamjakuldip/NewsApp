package com.kuldip.data

import com.kuldip.data.mapper.CreateNewsRequestEntityMapper
import com.kuldip.data.mapper.NewsArticleEntityMapper
import com.kuldip.data.model.NewsArticleEntity
import com.kuldip.data.repository.NewsArticleCacheDataStore
import com.kuldip.data.repository.NewsArticleRemoteDataStore
import com.kuldip.domain.model.CreateNewsRequest
import com.kuldip.domain.model.NewsArticle
import com.kuldip.domain.repository.NewsArticlesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsArticlesDataRepository @Inject constructor(
    private val remoteDataStore: NewsArticleRemoteDataStore,
    private val cacheDataStore: NewsArticleCacheDataStore,
    private val mapper: NewsArticleEntityMapper,
    private val mapperRequest: CreateNewsRequestEntityMapper,
) : NewsArticlesRepository {

    // NewsArticlesRepository Methods.
    override fun getNewsArticles(createNewsRequest: CreateNewsRequest): Single<List<NewsArticle>> {
        return getNewsArticlesFromRemoteAndSaveItToCache(createNewsRequest)
            .onErrorResumeNext { error ->
                return@onErrorResumeNext cacheDataStore.areNewsArticlesCached()
                    .flatMap { areCached ->
                        if (areCached) {
                            return@flatMap cacheDataStore.getNewsArticles(mapperRequest.mapFromEntity(createNewsRequest))
                        } else {
                            return@flatMap Single.error<List<NewsArticleEntity>>(error)
                        }
                    }
            }.map { newsArticles ->
                newsArticles.map { mapper.mapFromEntity(it) }
            }
    }

    // Private Methods.
    /**
     * A method gets NewsArticles from remote dataStore & saves it to cache dataStore & returns the newsArticles.
     */
    private fun getNewsArticlesFromRemoteAndSaveItToCache(createNewsRequest: CreateNewsRequest): Single<List<NewsArticleEntity>> {
        return remoteDataStore.getNewsArticles(mapperRequest.mapFromEntity(createNewsRequest))
            .flatMap { newsArticles ->
                if (createNewsRequest.page == 1) {
                    cacheDataStore.clearNewsArticles().andThen(cacheDataStore.saveNewsArticles(newsArticles))
                        .toSingleDefault(newsArticles)
                } else {
                    cacheDataStore.saveNewsArticles(newsArticles)
                        .toSingleDefault(newsArticles)
                }
            }
    }
}