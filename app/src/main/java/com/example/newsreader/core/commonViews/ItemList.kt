package com.example.newsreader.core.commonViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsreader.domain.models.Article
import com.example.newsreader.core.utils.Dimens.ExtraSmallPadding2
import com.example.newsreader.core.utils.Dimens.MediumPadding1


@Composable
fun ItemList(
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
                CardComponent(article = article, onClick = { onClick(article) })
            }
        }

    }

}


