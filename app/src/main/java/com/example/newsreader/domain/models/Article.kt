package com.example.newsreader.domain.models




data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val id: Int,
    val isBookmarked: Boolean = false
)
