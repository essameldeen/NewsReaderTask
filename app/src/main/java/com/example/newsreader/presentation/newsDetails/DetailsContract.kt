package com.example.newsreader.presentation.newsDetails

import com.example.newsreader.domain.models.Article

data class DetailsViewState(
    val isLoading: Boolean = false,
    val article: Article? = null,
    val error: String? = null,
    val isBookmarked: Boolean = false,
)

sealed class DetailsIntent {
    data class LoadArticleDetails(val articleId: String) : DetailsIntent()

object ToggleBookmarkArticle : DetailsIntent()


}