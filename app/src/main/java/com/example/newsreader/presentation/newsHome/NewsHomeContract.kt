package com.example.newsreader.presentation.newsHome

import androidx.paging.compose.LazyPagingItems
import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.models.ArticleListing


data class NewsHomeStat(
    val isLoading: Boolean = false,
    val articles: List<Article>? = null,
    val selectedArticle: Article? = null,
    val error: String? = null
)


sealed class NewsHomeIntent {
    object LoadArticles : NewsHomeIntent()
    data class SelectArticle(val article: Article) : NewsHomeIntent()
}

