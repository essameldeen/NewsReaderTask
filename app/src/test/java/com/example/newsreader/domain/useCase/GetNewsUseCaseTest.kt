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

class GetNewsUseCaseTest{
    private lateinit var getNewsUseCase: GetNewsUseCase
    private val repository: NewsRepository = mockk()


    @Before
    fun setup(){
        getNewsUseCase = GetNewsUseCase(repository)
    }

    @Test
    fun `invoke should return list of articles from repository`() = runTest {

        val newArticles = listOf(mockk<Article>(), mockk<Article>())
        coEvery { repository.getNews("us") } returns newArticles


        val result = getNewsUseCase.invoke("us")


        coVerify { repository.getNews("us") }
        assertEquals(newArticles, result)
    }


}