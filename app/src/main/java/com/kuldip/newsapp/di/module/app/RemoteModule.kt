package com.kuldip.newsapp.di.module.app

import com.kuldip.data.repository.NewsArticleRemoteDataStore
import com.kuldip.newsapp.di.component.ApplicationComponent
import com.kuldip.remote.NewsArticleRemoteDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteModule {

    @Binds
    abstract fun bindRemoteDataStore(newsArticleRemoteDataStoreImpl: NewsArticleRemoteDataStoreImpl): NewsArticleRemoteDataStore

}