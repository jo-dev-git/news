package com.jo.news.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.jo.news.data.model.Article
import com.jo.news.data.model.Headlines

@Composable
fun HeadlinesListView(headlines: Headlines, onItemClick: (Article) -> Unit) {
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .testTag("headlines_list")
    ) {
        ResultsTotalView(headlines)
        LazyColumn {
            val articles = headlines.articles ?: emptyList()
            itemsIndexed(articles) { index, article ->
                HeadlineItem(article, onItemClick = { onItemClick(it) }, index < articles.lastIndex)
            }
        }
    }
}