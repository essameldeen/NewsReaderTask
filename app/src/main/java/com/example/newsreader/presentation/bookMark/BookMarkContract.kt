package com.example.newsreader.presentation.bookMark

import com.example.newsreader.domain.models.Article

data class BookmarkViewState(
    val isLoading: Boolean = false,
    val bookmarkedArticles: List<Article> = emptyList(),
    val error: String? = null
)

sealed class BookmarkIntent {
    object LoadBookmarkedArticles : BookmarkIntent()

}