package com.example.newsreader.domain.useCase

import com.example.newsreader.domain.repository.NewsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UnBookMarkUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(id:Int){
        repository.unBookMarkArticle(id)
    }
}