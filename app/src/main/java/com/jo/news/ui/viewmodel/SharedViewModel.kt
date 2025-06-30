package com.jo.news.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jo.news.data.model.Article

class SharedViewModel : ViewModel() {

    var article by mutableStateOf<Article?>(null)
        private set

    fun addArticle(article: Article) {
        this.article = article
    }

}