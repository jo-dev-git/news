package com.jo.news.ui.screens

import androidx.compose.runtime.Composable
import com.jo.news.ui.view.ArticleView
import com.jo.news.ui.viewmodel.SharedViewModel

@Composable
fun ArticleScreen(sharedViewModel: SharedViewModel, back: () -> Unit) {
    val article = sharedViewModel.article
    article?.let { ArticleView(it, back) }
}

