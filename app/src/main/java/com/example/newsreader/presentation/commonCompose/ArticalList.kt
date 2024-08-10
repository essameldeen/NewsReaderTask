package com.example.newsreader.presentation.commonCompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.newsreader.domain.models.Article
import com.example.newsreader.domain.models.ArticleListing
import com.example.newsreader.utils.Dimens.ExtraSmallPadding2
import com.example.newsreader.utils.Dimens.MediumPadding1
import com.example.newsreader.utils.Dimens.MediumPadding2


@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()) EmptyScreen()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(ExtraSmallPadding2)
    ) {
        items(articles.size) {
            articles[it].let { article ->
                ArticleCard(article = article, onClick = { onClick(article) })
            }
        }

    }

}


@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(ExtraSmallPadding2)
        ) {
            items(articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }

        }
}



@Composable
 fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding2)) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding2)
            )
    }
}
