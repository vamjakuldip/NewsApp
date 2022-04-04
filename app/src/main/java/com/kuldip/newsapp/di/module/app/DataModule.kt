package com.kuldip.newsapp.di.module.app

import com.kuldip.data.NewsArticlesDataRepository
import com.kuldip.domain.repository.NewsArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(newsArticlesDataRepository: NewsArticlesDataRepository): NewsArticlesRepository

}