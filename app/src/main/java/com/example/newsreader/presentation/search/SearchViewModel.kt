package com.example.newsreader.presentation.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.domain.useCase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
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
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(SearchState())
    val viewState: StateFlow<SearchState> = _viewState

    private val searchQuery = MutableStateFlow("")
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val searchFlow = searchQuery
        .debounce(300)
        .filter { it.isNotEmpty() }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            flow {
                try {
                    val articles = getNewsUseCase.invoke(query)
                    emit(articles)
                } catch (e: Exception) {
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