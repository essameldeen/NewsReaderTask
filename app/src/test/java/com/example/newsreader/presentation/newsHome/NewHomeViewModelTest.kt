package com.example.newsreader.presentation.newsHome

import com.example.coroutines.MainDispatcherRule
import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.useCase.GetNewsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
@OptIn(ExperimentalCoroutinesApi::class)
class NewHomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    private  var useCase: GetNewsUseCase = mockk()

    private lateinit var viewModel: NewHomeViewModel


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        coEvery { useCase.invoke(any()) } returns emptyList()
    }


    @Test
    fun `initial state should be loading and then loaded with articles`() = runTest {
        viewModel = NewHomeViewModel(useCase)


        assertEquals(false, viewModel.viewState.first().isLoading)

        advanceUntilIdle()


        assertEquals(false, viewModel.viewState.first().isLoading)
        assertEquals(emptyList<Article>(), viewModel.viewState.first().articles)
    }
    @Test
    fun `getArticleListings should update state with articles`() = runTest {
        val result = listOf(Article(
                "author",
                "content",
                "description",
                "publishedAt",
               "source",
               "title",
              "url",
               "urlToImage",
              1,
               isBookmarked = false
            )
        )
        coEvery { useCase.invoke(any()) } returns result

        viewModel = NewHomeViewModel(useCase)


        advanceUntilIdle()


        val finalState = viewModel.viewState.first()
        assertEquals(false, finalState.isLoading)
        assertEquals(result, finalState.articles)
    }
    @Test
    fun `getArticleListings should update state with error on failure`() = runTest {
        val errorMessage = "Error occurred"
        coEvery { useCase.invoke(any()) } throws Exception(errorMessage)

        viewModel = NewHomeViewModel(useCase)


        advanceUntilIdle()


        val finalState = viewModel.viewState.first()
        assertEquals(false, finalState.isLoading)
        assertEquals(errorMessage, finalState.error)
    }

}

