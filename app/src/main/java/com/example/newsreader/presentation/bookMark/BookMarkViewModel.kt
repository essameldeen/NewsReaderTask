package com.example.newsreader.presentation.bookMark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.domain.useCase.GetAllBookmarkArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val getAllBookmarkArticleUseCase: GetAllBookmarkArticleUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow(BookmarkViewState())
    val viewState: StateFlow<BookmarkViewState> = _viewState


    init {
        handleIntent(BookmarkIntent.LoadBookmarkedArticles)

    }


    private fun loadBookmarkedArticles() {
        _viewState.value = BookmarkViewState(isLoading = true)
        viewModelScope.launch {
            try {
                val bookmarkedArticles = getAllBookmarkArticleUseCase.invoke()
                _viewState.value = BookmarkViewState(bookmarkedArticles = bookmarkedArticles)
            } catch (e: Exception) {
                _viewState.value = BookmarkViewState(error = e.message)
            }
        }
    }


    fun handleIntent(intent: BookmarkIntent) {
        when (intent) {
            is BookmarkIntent.LoadBookmarkedArticles -> loadBookmarkedArticles()
        }
    }


}