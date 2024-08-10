package com.example.newsreader.presentation.newsDetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsreader.R
import com.example.newsreader.domain.models.Article
import com.example.newsreader.presentation.commonCompose.EmptyScreen
import com.example.newsreader.presentation.commonCompose.ShimmerEffect
import com.example.newsreader.presentation.newsDetails.component.DetailsTopBar
import com.example.newsreader.utils.Dimens.ArticleImageHeight
import com.example.newsreader.utils.Dimens.MediumPadding1
import com.example.newsreader.utils.NewsConstant.NOT_AVAILABLE
import com.example.newsreader.utils.NewsConstant.TEXT_PLAIN

@Composable
fun NewsDetailsScreen(
    viewModel: NewsDetailsViewModel,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    val viewState by viewModel.viewState.collectAsState()

    when {
        viewState.isLoading -> repeat(10) {
            ShimmerEffect()
        }

        viewState.article != null -> {
            DetailsArticle(
                article = viewState.article,
                isBookMarked = viewState.isBookmarked,
                context,
                navigateUp,
                viewModel::handleIntent
            )
        }

        viewState.error != null -> {
            EmptyScreen(viewState.error)
        }
    }


}

@Composable
fun DetailsArticle(
    article: Article?,
    isBookMarked: Boolean,
    context: Context,
    navigateUp: () -> Unit,
    handleIntent: (DetailsIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(onBrowsingClick = {
            Intent(Intent.ACTION_VIEW).also {
                it.data = Uri.parse(article?.urlToImage)
                if (it.resolveActivity(context.packageManager) != null) {
                    context.startActivity(it)
                }
            }
        }, onBookMarkClick = {
            handleIntent(DetailsIntent.ToggleBookmarkArticle)
        }, onShareClick = {
            Intent(Intent.ACTION_SEND).also {
                it.putExtra(Intent.EXTRA_TEXT, article?.url)
                it.type = TEXT_PLAIN
                if (it.resolveActivity(context.packageManager) != null) {
                    context.startActivity(it)
                }
            }

        }, onBackClick = navigateUp,
            isBookMarked = isBookMarked
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(article?.urlToImage).build(),
                    contentDescription = "Details Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article?.title ?: NOT_AVAILABLE,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
                Text(
                    text = article?.content ?: NOT_AVAILABLE,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.body
                    )
                )
            }
        }
    }
}