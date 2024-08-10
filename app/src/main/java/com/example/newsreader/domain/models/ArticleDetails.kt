package com.example.newsreader.domain.models

data class ArticleDetails(
    val source: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val content: String?,
    val urlToImage: String?,
    val publishedAt: String,
)