package com.jo.news.ui.viewmodel

import com.jo.news.data.model.Article
import com.jo.news.data.model.Source
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class SharedViewModelTest {

    private lateinit var viewModel: SharedViewModel

    @Before
    fun setUp() {
        viewModel = SharedViewModel()
    }

    @Test
    fun getArticleReturnsCorrectArticle() {
        val article = Article(
            Source("id", "name"),
            "author",
            "title",
            "description",
            "url",
            "urlToImage",
            "date",
            "content"
        )

        viewModel.addArticle(article)
        assertEquals(article, viewModel.article)
    }

    @Test
    fun getArticleReturnsNullInitially() {
        assertEquals(null, viewModel.article)
    }
}