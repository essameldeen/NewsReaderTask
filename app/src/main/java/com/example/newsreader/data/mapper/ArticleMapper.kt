package com.example.newsreader.data.mapper

import com.example.newsreader.data.dataSource.local.ArticleEntity
import com.example.newsreader.data.dataSource.remote.response.ArticleDto
import com.example.newsreader.domain.models.Article


fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
        title = title ?: "",
        source = source?.name ?: "",
        publishedAt = publishedAt ?: "",
        content = content ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        author = author ?: "",
        description = description ?: ""
    )
}

fun ArticleDto.toDomainModel(): Article {
    return Article(
        title = title ?: "",
        source = source?.name ?: "",
        publishedAt = publishedAt ?: "",
        content = content ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        author = author ?: "",
        description = description ?: ""
    )
}

fun ArticleEntity.toDomainModel(): Article {
    return Article(
        title = title,
        source = source,
        publishedAt = publishedAt,
        content = content,
        url = url,
        urlToImage = urlToImage,
        author = author,
        description = description,
        id = id,
        isBookmarked = isBookmarked

    )
}

fun Article.ArticleEntity(): ArticleEntity {
    return ArticleEntity(
        title = title,
        source = source,
        publishedAt = publishedAt,
        content = content,
        url = url,
        urlToImage = urlToImage,
        author = author,
        description = description,
        isBookmarked = isBookmarked

    )
}
