package com.example.newsreader.presentation.newsHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.domain.useCase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewHomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase

) : ViewModel() {
    private val _viewState = MutableStateFlow(NewsHomeState())
    val viewState: StateFlow<NewsHomeState> = _viewState

    init {
        handleIntent(NewsHomeIntent.LoadArticles)
    }

    fun handleIntent(intent: NewsHomeIntent) {
        when (intent) {
            NewsHomeIntent.LoadArticles -> getArticleListings()
        }
    }


     fun getArticleListings(
        query: String = "bitcoin",
    ) = viewModelScope.launch {
        _viewState.value = _viewState.value.copy(isLoading = true)
        try {
            val articles = getNewsUseCase.invoke(query)
            _viewState.value = _viewState.value.copy(isLoading = false, articles = articles)
        } catch (e: Exception) {
            _viewState.value = _viewState.value.copy(isLoading = false, error = e.message)
        }

    }

}