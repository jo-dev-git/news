package com.jo.news.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jo.news.R
import com.jo.news.data.model.Article

@Composable
fun ArticleView(article: Article, back: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .testTag("article")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = article.title ?: "",
                style = MaterialTheme.typography.titleLarge
            )
            HorizontalDivider(modifier = Modifier.padding(10.dp))
            AsyncImageHeadlineView(article.urlToImage!!, Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = article.description ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontStyle = FontStyle.Italic,
                text = stringResource(R.string.find_full_article, article.url!!),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontStyle = FontStyle.Italic,
                text = article.author ?: "",
                style = MaterialTheme.typography.bodySmall
            )
        }
        ElevatedButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .testTag(stringResource(R.string.back)),
            onClick = back,
        ) {
            Text(text = stringResource(R.string.back))
        }
    }
}