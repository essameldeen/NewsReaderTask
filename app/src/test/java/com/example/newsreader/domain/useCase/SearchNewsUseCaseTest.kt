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

class SearchNewsUseCaseTest{
    private lateinit var searchNewsUseCase: SearchNewsUseCase
    private val repository: NewsRepository = mockk()


    @Before
    fun setup(){
        searchNewsUseCase = SearchNewsUseCase(repository)
    }

    @Test
    fun `invoke should return result of search list of articles  from repository`() = runTest {

        val newArticles = listOf(mockk<Article>(), mockk<Article>())
        coEvery { repository.searchForNews("us") } returns newArticles

        val result = searchNewsUseCase.invoke("us")

        coVerify { repository.searchForNews("us") }
        assertEquals(newArticles, result)
    }

}