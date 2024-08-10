package com.example.newsreader.presentation.newsDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.domain.useCase.GetNewsByIdUseCase
import com.example.newsreader.domain.useCase.UnBookMarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val getNewsByIdUseCase: GetNewsByIdUseCase,
    private val bookMarkUseCase: UnBookMarkUseCase,
    private val unBookMarkUseCase: UnBookMarkUseCase

) : ViewModel() {


    private val _viewState = MutableStateFlow(DetailsViewState())
    val viewState: StateFlow<DetailsViewState> = _viewState


    fun handleIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.LoadArticleDetails -> loadArticleDetails(intent.articleId)
            DetailsIntent.BookmarkArticle -> if (_viewState.value.isBookmarked) unBookmarkArticle() else bookmarkArticle()

            DetailsIntent.UnBookmarkArticle -> unBookmarkArticle()
        }
    }

    fun loadArticleDetails(articleId: String) = viewModelScope.launch {
        _viewState.value = DetailsViewState(isLoading = true)

        try {
            val article = getNewsByIdUseCase.invoke(articleId)
            _viewState.value = DetailsViewState(article = article)
        } catch (e: Exception) {
            _viewState.value = DetailsViewState(error = e.message)
        }
    }

    fun bookmarkArticle() {
        viewModelScope.launch {
            _viewState.value.article?.let { article ->
                bookMarkUseCase.invoke(article.id ?: 0)
                _viewState.value = _viewState.value.copy(isBookmarked = true, article = article.copy(isBookmarked = true))
            }
        }
    }

    fun unBookmarkArticle() {
        viewModelScope.launch {
            _viewState.value.article?.let { article ->
                unBookMarkUseCase.invoke(article.id ?: 0)
                _viewState.value = _viewState.value.copy(isBookmarked = false, article = article.copy(isBookmarked = false))
            }
        }
    }

}

