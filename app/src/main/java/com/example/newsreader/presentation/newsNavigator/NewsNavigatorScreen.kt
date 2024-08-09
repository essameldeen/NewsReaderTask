package com.example.newsreader.presentation.newsNavigator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NewsNavigatorScreen(){
    Scaffold (  modifier = Modifier.fillMaxSize(),) {
        Column {
            Text(text = "News Navigaor")
        }
    }
}