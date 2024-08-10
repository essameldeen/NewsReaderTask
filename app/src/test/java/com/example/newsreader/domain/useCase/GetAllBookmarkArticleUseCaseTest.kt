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

class GetAllBookmarkArticleUseCaseTest{
    private lateinit var getAllBookmarkArticleUseCase: GetAllBookmarkArticleUseCase
    private val repository: NewsRepository = mockk()

    @Before
    fun setUp() {
        getAllBookmarkArticleUseCase = GetAllBookmarkArticleUseCase(repository)
    }

    @Test
    fun `invoke should return list of bookmarked articles from repository`() = runTest {

        val bookmarkedArticles = listOf(mockk<Article>(), mockk<Article>())
        coEvery { repository.getAllBookmarkedArticles() } returns bookmarkedArticles


        val result = getAllBookmarkArticleUseCase.invoke()


        coVerify { repository.getAllBookmarkedArticles() }
        assertEquals(bookmarkedArticles, result)
    }
}