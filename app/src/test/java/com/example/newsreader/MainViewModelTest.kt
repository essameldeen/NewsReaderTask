package com.example.newsreader

import com.example.coroutines.MainDispatcherRule
import com.example.newsreader.domain.useCase.ReadAppEntryUseCase
import com.example.newsreader.presentation.navigation.Route
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class MainViewModelTest {


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Mock
    private lateinit var readAppEntryUseCase: ReadAppEntryUseCase

    private lateinit var viewModel: MainViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        whenever(readAppEntryUseCase.invoke()).thenReturn(flowOf(true))
    }

    @Test
    fun `test splashCondition and startDestination when shouldStartFromHomeScreen is true`() =
        runTest {
            viewModel = MainViewModel(readAppEntryUseCase)
            assertEquals(Route.NewsNavigation.route, viewModel.startDestination)

        }

    @Test
    fun `test splashCondition and startDestination when shouldStartFromHomeScreen is false`() =
        runTest {
            viewModel = MainViewModel(readAppEntryUseCase)
            whenever(readAppEntryUseCase.invoke()).thenReturn(flowOf(true))
            assertEquals(Route.NewsNavigation.route, viewModel.startDestination)

        }

}