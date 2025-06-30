package com.jo.news.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.jo.news.data.HeadlinesUiState
import com.jo.news.ui.view.HeadlinesErrorView
import com.jo.news.ui.view.HeadlinesListView
import com.jo.news.ui.view.HeadlinesLoadingView
import com.jo.news.ui.viewmodel.HeadlineViewModel
import com.jo.news.ui.viewmodel.SharedViewModel
import java.util.Locale

@Composable
fun HeadlinesScreen(
    headlineViewModel: HeadlineViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    onItemClick: () -> Unit
) {
    val language = Locale.getDefault().country.lowercase()
    val screenState by headlineViewModel.headlines.observeAsState(initial = HeadlinesUiState.Loading)

    LaunchedEffect(language) {
        headlineViewModel.getHeadlines(language)
    }

    when (screenState) {
        is HeadlinesUiState.Loading -> HeadlinesLoadingView()
        is HeadlinesUiState.Success -> HeadlinesListView(
            (screenState as HeadlinesUiState.Success).headlines,
            onItemClick = {
                onItemClick()
                sharedViewModel.addArticle(article = it)
            })

        is HeadlinesUiState.Error -> HeadlinesErrorView((screenState as HeadlinesUiState.Error).message)
    }
}


