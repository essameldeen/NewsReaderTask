package com.example.newsreader.data.dataSource.remote.response




data class NewsResponse(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)

data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
)

data class Source(
    val id: String?,
    val name: String?
)

