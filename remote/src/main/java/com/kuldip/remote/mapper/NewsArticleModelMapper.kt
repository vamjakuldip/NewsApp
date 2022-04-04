package com.kuldip.remote.mapper

import com.kuldip.data.model.NewsArticleEntity
import com.kuldip.remote.model.NewsArticleModel
import com.kuldip.remote.util.DateUtil
import java.util.*
import javax.inject.Inject

class NewsArticleModelMapper @Inject constructor() : ModelMapper<NewsArticleModel, NewsArticleEntity> {

    companion object {
        const val PUBLISHED_AT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX"
    }

    override fun mapFromModel(model: NewsArticleModel): NewsArticleEntity {
        val publishedAtDate =
            DateUtil.convertStringToDate((model.publishedAt ?: ""), PUBLISHED_AT_DATE_FORMAT) ?: Date()
        return NewsArticleEntity(
            model.url ?: "", // here source is null so used image url for unique identifier
            model.title ?: "",
            model.description ?: "",
            model.author,
            model.source?.name ?: "",
            publishedAtDate,
            model.url ?: "",
            model.urlToImage ?: ""
        )
    }
}