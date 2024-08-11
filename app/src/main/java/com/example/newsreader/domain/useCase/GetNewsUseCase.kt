package com.example.newsreader.domain.useCase

import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.repository.NewsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


class GetNewsUseCase @Inject constructor  (
     private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(
        searchQuery: String
    ): List<Article> =newsRepository.getNews(searchQuery)

}