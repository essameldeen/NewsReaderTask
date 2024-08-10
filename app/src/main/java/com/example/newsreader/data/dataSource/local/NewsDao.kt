package com.example.newsreader.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM articles WHERE urlToImage = :id")
    suspend fun getArticlesById(id: String): ArticleEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE title LIKE '%' || :query || '%' OR  :query || '%'")
    suspend fun searchArticles(query: String): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE isBookmarked = 1")
    suspend fun getAllBookmarkedArticles(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity)

}


