package com.kuldip.data.mapper

import com.kuldip.data.model.NewsArticleEntity
import com.kuldip.domain.model.NewsArticle
import javax.inject.Inject

open class NewsArticleEntityMapper @Inject constructor() : EntityMapper<NewsArticleEntity, NewsArticle> {

    override fun mapFromEntity(entity: NewsArticleEntity): NewsArticle {
        return NewsArticle(
            entity.title,
            entity.description,
            entity.author,
            entity.source,
            entity.publishedAt,
            entity.url,
            entity.urlToImage
        )
    }
}