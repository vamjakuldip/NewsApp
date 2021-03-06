package com.kuldip.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kuldip.cache.dao.CachedNewsArticlesDao
import com.kuldip.cache.model.CachedNewsArticle
import javax.inject.Inject
import javax.inject.Singleton


@Database(entities = [CachedNewsArticle::class], version = 1)
@TypeConverters(DateConverter::class)
@Singleton
abstract class NewsArticlesDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedNewsArticlesDao(): CachedNewsArticlesDao

    companion object {

        private var INSTANCE: NewsArticlesDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): NewsArticlesDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NewsArticlesDatabase::class.java, NewsArticleDBConstants.DATABASE_NAME
                        ).build()
                    }
                    return INSTANCE as NewsArticlesDatabase
                }
            }
            return INSTANCE as NewsArticlesDatabase
        }
    }

}