package com.jo.news.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.jo.news.R

@Composable
fun AsyncImageHeadlineView(url: String?, modifier: Modifier) {
    AsyncImage(
        model = url ?: "",
        contentDescription = null,
        placeholder = painterResource(R.drawable.baseline_image_not_supported_24),
        error = painterResource(R.drawable.baseline_image_not_supported_24),
        modifier = modifier
    )
}
