package com.example.newsreader.presentation.onBoarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsreader.R
import com.example.newsreader.domain.models.Page
import com.example.newsreader.domain.models.pages
import com.example.newsreader.ui.theme.NewsReaderTheme
import com.example.newsreader.utils.Dimens.MediumPadding1
import com.example.newsreader.utils.Dimens.MediumPadding2

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {

    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = page.image), contentDescription = "Image-BackGround"
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = page.title, modifier =
            Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )
        Text(
            text = page.title, modifier =
            Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    NewsReaderTheme {
        OnBoardingPage(page = pages[0])
    }

}