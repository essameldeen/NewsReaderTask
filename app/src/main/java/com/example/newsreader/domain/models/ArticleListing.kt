package com.example.newsreader.domain.models

data class ArticleListing(
    val source: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val urlImage:String,
    val id: Int?
)
