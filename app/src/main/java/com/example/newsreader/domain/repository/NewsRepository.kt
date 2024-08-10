package com.example.newsreader.domain.repository

import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.models.ArticleDetails
import com.example.newsreader.domain.models.ArticleListing
import com.example.newsreader.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(
        searchQuery: String
    ): List<Article>

    suspend fun getArticleDetailsById(
        id: String
    ): Article

    suspend fun searchForNews(
        searchQuery: String
    ): List<Article>

    suspend fun getAllBookmarkedArticles(): List<Article>

    suspend fun isArticleBookmarked(articleId: String): Boolean

    suspend fun unBookMarkArticle(articleId: Int)
    suspend fun bookMarkArticle(articleId: Int)
}