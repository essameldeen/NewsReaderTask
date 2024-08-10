package com.example.newsreader.domain.useCase

import androidx.paging.PagingData
import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.models.ArticleListing
import com.example.newsreader.domain.repository.NewsRepository
import com.example.newsreader.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetNewsUseCase @Inject constructor(
     private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(
        searchQuery: String
    ): List<Article> =newsRepository.getNews(searchQuery)

}