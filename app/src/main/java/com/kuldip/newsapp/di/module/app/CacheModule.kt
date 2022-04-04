package com.kuldip.newsapp.di.module.app

import android.app.Application
import com.kuldip.cache.NewsArticleCacheDataStoreImpl
import com.kuldip.cache.db.NewsArticlesDatabase
import com.kuldip.data.repository.NewsArticleCacheDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class CacheModule {

    companion object {
        @Provides
        fun providesDataBase(application: Application): NewsArticlesDatabase {
            return NewsArticlesDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindCacheDataStore(cacheDataStoreImpl: NewsArticleCacheDataStoreImpl): NewsArticleCacheDataStore

}