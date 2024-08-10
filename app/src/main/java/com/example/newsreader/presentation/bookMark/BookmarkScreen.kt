package com.example.newsreader.presentation.bookMark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newsreader.R
import com.example.newsreader.presentation.commonCompose.ArticleList
import com.example.newsreader.presentation.commonCompose.ShimmerEffect
import com.example.newsreader.utils.Dimens.MediumPadding1


@Composable
fun BookMarkScreen(
    viewModel: BookMarkViewModel,
    navigateToDetails: (String) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = MediumPadding1, start = MediumPadding1, end = MediumPadding1
            )
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        when {
            viewState.bookmarkedArticles.isNotEmpty() -> {
                ArticleList(articles = viewState.bookmarkedArticles) { article ->
                    navigateToDetails(article.urlToImage)
                }
            }

            viewState.isLoading -> {
                repeat(10) {
                    ShimmerEffect()
                }
            }

            viewState.error != null -> {
                Text(text = viewState.error.toString())
            }
        }


    }

}