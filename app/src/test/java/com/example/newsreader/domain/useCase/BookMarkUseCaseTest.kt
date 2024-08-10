package com.example.newsreader.domain.useCase

import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.repository.NewsRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class BookMarkUseCaseTest{
    private lateinit var bookMarkUseCase: BookMarkUseCase
    private val repository: NewsRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        bookMarkUseCase = BookMarkUseCase(repository)
    }

    @Test
    fun `invoke should call bookMarkArticle on repository`() = runTest {
        val article = mockk<Article>()
        bookMarkUseCase.invoke(article)
        coVerify { repository.bookMarkArticle(article) }
    }
}