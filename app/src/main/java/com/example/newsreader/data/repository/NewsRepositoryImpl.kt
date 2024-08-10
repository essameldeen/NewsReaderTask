package com.example.newsreader.data.repository

import android.content.Context
import android.net.ConnectivityManager
import com.example.newsreader.data.dataSource.local.NewsDao
import com.example.newsreader.data.dataSource.remote.api.NewsApiService
import com.example.newsreader.data.mapper.toDomainModel
import com.example.newsreader.data.mapper.toEntity
import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.models.ArticleDetails
import com.example.newsreader.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService,
    private val newsDao: NewsDao,
    private val context: Context
) : NewsRepository {
    override suspend fun getNews(searchQuery: String): List<Article> {
        return if (isNetworkAvailable(context)) {
            val response = api.getNews(searchQuery)
            val articles = response.articles

            newsDao.insertArticles(articles.map { it.toEntity() })
            articles.map { it.toDomainModel() }
        } else {
            newsDao.getArticles().map {
                it.toDomainModel()
            }
        }
    }

    override suspend fun getArticleDetailsById(id: String): Article {
        return newsDao.getArticlesById(id).toDomainModel()

    }


    override suspend fun searchForNews(searchQuery: String): List<Article> {
        return if (isNetworkAvailable(context)) {
            val response = api.getNews(searchQuery)
            val articles = response.articles
            articles.map { it.toDomainModel() }
        } else {
            newsDao.searchArticles(searchQuery).map { it.toDomainModel() }
        }
    }

    override suspend fun getAllBookmarkedArticles(): List<Article> {
        return newsDao.getAllBookmarkedArticles().map { it.toDomainModel() }
    }

    override suspend fun isArticleBookmarked(articleId: String): Boolean {
        return newsDao.getArticlesById(articleId).isBookmarked
    }

    override suspend fun bookMarkArticle(articleId: Int) {
        newsDao.updateBookmarkStatus(articleId, isBookmarked = true)
    }

    override suspend fun unBookMarkArticle(articleId: Int) {
        newsDao.updateBookmarkStatus(articleId, isBookmarked = false)
    }
}

private fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}