package com.example.newsreader.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val id: Int ,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt:String,
    val content: String,
    val source:String,
    val isBookmarked: Boolean = false,
)