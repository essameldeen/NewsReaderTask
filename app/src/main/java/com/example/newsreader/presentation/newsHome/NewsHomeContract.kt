package com.example.newsreader.presentation.newsHome

import com.example.newsreader.domain.models.Article


data class NewsHomeState(
    val isLoading: Boolean = false,
    val articles: List<Article>? = null,
    val error: String? = null
)


sealed class NewsHomeIntent {
    object LoadArticles : NewsHomeIntent()
}

