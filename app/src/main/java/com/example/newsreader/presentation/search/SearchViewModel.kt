package com.example.newsreader.presentation.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.domain.useCase.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(SearchState())
    val viewState: StateFlow<SearchState> = _viewState

    private val searchQuery = MutableStateFlow("")
    private val searchFlow = searchQuery
        .debounce(300)
        .filter { it.isNotEmpty() }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            flow {
                try {
                    val articles = searchNewsUseCase.invoke(query)
                    emit(articles)
                } catch (e: Exception) {
                    // Emit an empty list and update the error state
                    emit(emptyList())
                    _viewState.value = SearchState(error = e.message)
                }
            }
        }

    init {
        viewModelScope.launch {
            searchFlow.collect { articles ->
                _viewState.value = SearchState(articles = articles)
                if (articles.isNotEmpty()) {
                    _viewState.value = SearchState(articles = articles)
                } else if (_viewState.value.error == null) {
                    // Handle the case when no articles are found and no error occurred
                    _viewState.value = SearchState(error = "No articles found.")
                }


            }
        }
    }


    fun handleIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.SearchNews -> {
                searchArticles(intent.query)
            }

            is SearchIntent.NavigateToDetailsScreen -> {
                _viewState.value = _viewState.value.copy(selectedArticleId = intent.id)
            }

            is SearchIntent.UpdateQuery -> {
                _viewState.value = _viewState.value.copy(query = intent.query)
            }
        }
    }


    private fun searchArticles(query: String) {
        searchQuery.value = query
        _viewState.value = SearchState(isLoading = true)
    }
}