package com.jo.news.ui.screens

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.jo.news.R
import com.jo.news.data.model.Article
import com.jo.news.data.model.Source
import com.jo.news.ui.viewmodel.SharedViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ArticleScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: SharedViewModel
    private lateinit var mockBack: () -> Unit

    private val article = Article(
        Source("id", "name"),
        "author",
        "title",
        "description",
        "url",
        "urlToImage",
        "date",
        "content"
    )

    @Before
    fun setUp() {
        mockBack = mock()
        viewModel = SharedViewModel()
        viewModel.addArticle(article)
    }

    @Test
    fun displayCorrectArticleDetails() {
        composeTestRule.setContent { ArticleScreen(viewModel, back = mockBack) }
        composeTestRule.onNodeWithText(article.title ?: "").assertIsDisplayed()
        composeTestRule.onNodeWithText(article.author ?: "", substring = true).assertIsDisplayed()
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val backText = context.resources.getString(R.string.back)
        composeTestRule.onNodeWithText(backText).assertIsDisplayed()
    }

    @Test
    fun performBackClick() {
        composeTestRule.setContent { ArticleScreen(viewModel, back = mockBack) }
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val backText = context.resources.getString(R.string.back)

        try {
            composeTestRule.onNodeWithTag(backText).performClick()
            verify(mockBack).invoke()
        } catch (e: AssertionError) {
            throw e
        }
    }
}