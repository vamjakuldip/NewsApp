package com.kuldip.presentation.mapper

import com.kuldip.domain.model.NewsArticle
import com.kuldip.presentation.model.NewsArticleUIModel
import com.kuldip.presentation.util.DateUtil
import javax.inject.Inject

open class NewsArticleUIMapper @Inject constructor() : Mapper<NewsArticleUIModel, NewsArticle> {

    companion object {
        const val PUBLISHED_AT_DISPLAY_DATE_FORMAT = "MMM d, yyyy"
    }

    override fun mapToView(type: NewsArticle): NewsArticleUIModel {
        val publishedAtDisplayDate = DateUtil.convertDateToString(type.publishedAt, PUBLISHED_AT_DISPLAY_DATE_FORMAT)
        return NewsArticleUIModel(type.title, type.source, publishedAtDisplayDate, type.url, type.urlToImage)
    }
}