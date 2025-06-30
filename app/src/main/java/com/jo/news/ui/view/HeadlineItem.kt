package com.jo.news.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.jo.news.data.model.Article

@Composable
fun HeadlineItem(article: Article, onItemClick: (Article) -> Unit, isTheLastItem: Boolean) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClick(article)
            }
            .testTag("headlines_item")
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = article.title!!,
            style = MaterialTheme.typography.titleSmall
        )
        AsyncImageHeadlineView(
            article.urlToImage, Modifier
                .padding(8.dp)
                .height(90.dp)
                .width(90.dp)
        )
    }
    if (isTheLastItem)
        HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))
}