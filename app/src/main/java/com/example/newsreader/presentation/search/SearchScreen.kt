package com.example.newsreader.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.room.util.query
import com.example.newsreader.domain.models.Article
import com.example.newsreader.presentation.commonCompose.ArticleList
import com.example.newsreader.presentation.commonCompose.SearchBar
import com.example.newsreader.presentation.commonCompose.ShimmerEffect
import com.example.newsreader.utils.Dimens.MediumPadding1


@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToDetails: (String) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = viewState.query,
            readOnly = false,
            onValueChange = {
                viewModel.handleIntent(SearchIntent.UpdateQuery(it))
            },
            onSearch = { searchQuery ->
                viewModel.handleIntent(SearchIntent.SearchNews(searchQuery))
            })

        Spacer(modifier = Modifier.height(MediumPadding1))


        when {
            viewState.isLoading -> repeat(10) {
                ShimmerEffect()
            }

            viewState.articles != null -> {
                ArticleList(
                    modifier = Modifier.padding(horizontal = MediumPadding1),
                    articles = viewState.articles!!
                ) { article ->
                    navigateToDetails(article.urlToImage )
                }
            }

            viewState.selectedArticleId != null -> {}//handle details screen
            viewState.error != null -> {
                Text("Error: ${viewState.error}")
            }
        }


    }

}