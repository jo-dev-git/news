package com.jo.news.data

import com.jo.news.data.model.Headlines

sealed class HeadlinesUiState {
    data object Loading : HeadlinesUiState()
    data class Success(val headlines: Headlines) : HeadlinesUiState()
    data class Error(val message: String) : HeadlinesUiState()
}