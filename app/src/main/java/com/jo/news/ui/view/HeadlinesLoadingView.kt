package com.jo.news.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun HeadlinesLoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("headlines_loading"),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}