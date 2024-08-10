package com.example.newsreader.presentation.newsDetails.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.newsreader.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    isBookMarked: Boolean,
    onBrowsingClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
) {


    TopAppBar(title = { },
        Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Back Arrow"
                )
            }
        },
        actions = {
            IconButton(onClick = onBookMarkClick) {
                Icon(
                    painter = if (isBookMarked) painterResource(id = R.drawable.ic_un_bookmark) else painterResource(
                        id = R.drawable.ic_bookmark
                    ),
                    contentDescription = "BookMark Icon"
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share Icon"
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = "Network Icon"
                )
            }

        }
    )
}
