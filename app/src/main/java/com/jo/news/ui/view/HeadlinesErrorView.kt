package com.jo.news.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.jo.news.R

@Composable
fun HeadlinesErrorView(message: String = stringResource(R.string.error_headlines)) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("headlines_error"),
        contentAlignment = Alignment.Center
    ) {
        Text(message)
    }
}