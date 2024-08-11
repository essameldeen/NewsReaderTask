package com.example.newsreader.domain.useCase

import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.repository.NewsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class GetNewsByIdUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun invoke(id: String): Article {
        return newsRepository.getArticleDetailsById(id)
    }
}