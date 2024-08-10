package com.example.newsreader.presentation.search

import com.example.newsreader.domain.models.Article

data class SearchState(
    val isLoading: Boolean = false,
    val articles: List<Article>? = null,
    val error: String? = null,
    val selectedArticleId: Int? = null,
    val query: String = ""
)

sealed class SearchIntent {
    data class UpdateQuery(val query: String) : SearchIntent()
    data class SearchNews(val query: String) : SearchIntent()
    data class NavigateToDetailsScreen(val id: Int) : SearchIntent()
}