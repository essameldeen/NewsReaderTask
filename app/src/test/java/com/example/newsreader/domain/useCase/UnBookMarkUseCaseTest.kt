package com.example.newsreader.domain.useCase

import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.repository.NewsRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
 import org.junit.Before
import org.junit.Test

class UnBookMarkUseCaseTest{
    private lateinit var unBookMarkUseCase: UnBookMarkUseCase
    private val repository: NewsRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        unBookMarkUseCase = UnBookMarkUseCase(repository)
    }

    @Test
    fun `invoke should call unBookMarkArticle on repository`() = runTest {
        val article = mockk<Article>()
        unBookMarkUseCase.invoke(article)
        coVerify { repository.unBookMarkArticle(article) }
    }
}