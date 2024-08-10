package com.example.newsreader.domain.repository

import com.example.newsreader.domain.models.Article

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

    suspend fun unBookMarkArticle(article: Article)
    suspend fun bookMarkArticle(article: Article)
}