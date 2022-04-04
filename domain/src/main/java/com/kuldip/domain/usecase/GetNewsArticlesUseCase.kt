package com.kuldip.domain.usecase

import com.kuldip.domain.model.CreateNewsRequest
import com.kuldip.domain.model.NewsArticle
import com.kuldip.domain.repository.NewsArticlesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class GetNewsArticlesUseCase @Inject constructor(private val newsArticlesRepository: NewsArticlesRepository) {

    open fun execute(createNewsRequest: CreateNewsRequest): Single<List<NewsArticle>> {
        return newsArticlesRepository.getNewsArticles(createNewsRequest)
    }
}
