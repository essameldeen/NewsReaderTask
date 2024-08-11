package com.example.newsreader.data.mapper

import com.example.newsreader.data.entity.ArticleEntity
import com.example.newsreader.data.dataSource.remote.response.ArticleDto
import com.example.newsreader.domain.models.Article
import kotlin.random.Random




fun ArticleDto.toDomainModel(): Article {
    return Article(
        title = title ?: "",
        source = source?.name ?: "",
        publishedAt = publishedAt ?: "",
        content = content ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        author = author ?: "",
        description = description ?: "",
        id = Random.nextInt()
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

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = id,
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
