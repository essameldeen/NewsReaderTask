package com.example.newsreader.presentation.newsHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsreader.R
import com.example.newsreader.presentation.commonCompose.ArticleList
import com.example.newsreader.presentation.commonCompose.SearchBar
import com.example.newsreader.presentation.commonCompose.ShimmerEffect
import com.example.newsreader.utils.Dimens.MediumPadding1

@Composable
fun NewsHomeScreen(
    viewModel: NewHomeViewModel,
    navigateToSearch: () -> Unit,
    navigateToDetails: (String) -> Unit,
) {

    val viewState by viewModel.viewState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigateToSearch()
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
                    navigateToDetails(article.urlToImage)
                }
            }

            viewState.selectedArticle != null -> {}//handle details screen
            viewState.error != null -> {
                Text("Error: ${viewState.error}")
            }
        }


    }

}