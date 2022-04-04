package com.kuldip.cache.mapper

import com.kuldip.cache.model.CachedNewsArticle
import com.kuldip.data.model.NewsArticleEntity
import javax.inject.Inject

class CachedNewsArticleMapper @Inject constructor() : CacheMapper<CachedNewsArticle, NewsArticleEntity> {

    override fun mapFromCached(cache: CachedNewsArticle): NewsArticleEntity {
        return NewsArticleEntity(
            cache.sourceId, cache.title, cache.description, cache.author,
            cache.source, cache.publishedAt, cache.url, cache.urlToImage
        )
    }

    override fun mapToCached(entity: NewsArticleEntity): CachedNewsArticle {
        return CachedNewsArticle(
            entity.sourceId, entity.title, entity.description, entity.author,
            entity.source, entity.publishedAt, entity.url, entity.urlToImage
        )
    }
}