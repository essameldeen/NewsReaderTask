package com.example.newsreader.domain.useCase

import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetNewsByIdUseCaseTest {
    private lateinit var getNewsByIdUseCase: GetNewsByIdUseCase
    private val newsRepository: NewsRepository = mockk()


    @Before
    fun setup() {
        getNewsByIdUseCase = GetNewsByIdUseCase(newsRepository)
    }

    @Test
    fun `invoke should return article by id from articles from repository `() = runTest {
        val article = mockk<Article>()
        coEvery { newsRepository.getArticleDetailsById("123") } returns article


        val result = getNewsByIdUseCase.invoke("123")


        coVerify { newsRepository.getArticleDetailsById("123") }
        assertEquals(article, result)
    }
}