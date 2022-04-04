package com.kuldip.remote.api

import com.kuldip.data.model.CreateNewsRequestEntity


class Repository(private val apiService: ApiService) {
    fun getNewsArticles(apiKey: String?, createNewsRequest: CreateNewsRequestEntity) = apiService.getNewsArticles(apiKey = apiKey, pageSize = createNewsRequest.pageSize, page = createNewsRequest.page)
}
