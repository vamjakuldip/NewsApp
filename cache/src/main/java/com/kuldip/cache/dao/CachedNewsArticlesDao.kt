package com.kuldip.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kuldip.cache.db.NewsArticleDBConstants
import com.kuldip.cache.model.CachedNewsArticle
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


@Dao
interface CachedNewsArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsArticles(newsArticles: List<CachedNewsArticle>): Completable

    @Query("SELECT * FROM news_articles ORDER BY id DESC LIMIT :pageSize OFFSET :offset")
    fun getNewsArticles(pageSize: Int, offset: Int): Single<List<CachedNewsArticle>>

    @Query(NewsArticleDBConstants.QUERY_NEWS_ARTICLES_COUNT)
    fun getNewsArticlesCount(): Single<Int>


    @Query(NewsArticleDBConstants.DELETE_NEWS_ARTICLES)
    fun deleteNewsArticles(): Completable

}