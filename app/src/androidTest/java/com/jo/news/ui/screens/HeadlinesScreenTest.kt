package com.jo.news.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jo.news.data.HeadlinesUiState
import com.jo.news.data.model.Article
import com.jo.news.data.model.Headlines
import com.jo.news.data.model.Source
import com.jo.news.ui.viewmodel.HeadlineViewModel
import com.jo.news.ui.viewmodel.SharedViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeadlinesScreenTest {

    @get:Rule()
    val composeTestRule = createComposeRule()

    private val testArticles = listOf(
        Article(
            Source("id", "name"),
            "author",
            "Test title Article 1",
            "description",
            "url",
            "urlToImage2",
            "date",
            "content"
        ),
        Article(
            Source("id2", "name2"),
            "author2",
            "Test title Article 2",
            "description2",
            "http://url.com",
            "urlToImage2",
            "date2",
            "content2"
        ),
    )

    private val testHeadlines = Headlines("ok", 2, testArticles)

    private lateinit var mockViewModel: HeadlineViewModel
    private lateinit var mockSharedViewModel: SharedViewModel

    @Before
    fun setUp() {
        mockViewModel = mockk<HeadlineViewModel>(relaxed = true)
        mockSharedViewModel = mockk<SharedViewModel>(relaxed = true)
    }

    @Test
    fun displayLoadingScreen() {
        val loadingState = MutableLiveData<HeadlinesUiState>(HeadlinesUiState.Loading)
        every { mockViewModel.headlines } returns loadingState

        composeTestRule.setContent {
            HeadlinesScreen(
                headlineViewModel = mockViewModel,
                sharedViewModel = mockSharedViewModel,
                onItemClick = { }
            )
        }
        composeTestRule.onNode(hasTestTag("headlines_loading")).assertIsDisplayed()
    }

    @Test
    fun displayListWhenSuccess() {
        val successState =
            MutableLiveData<HeadlinesUiState>(HeadlinesUiState.Success(testHeadlines))
        every { mockViewModel.headlines } returns successState

        composeTestRule.setContent {
            HeadlinesScreen(
                headlineViewModel = mockViewModel,
                sharedViewModel = mockSharedViewModel,
                onItemClick = { }
            )
        }

        composeTestRule.onNode(hasTestTag("headlines_list")).assertIsDisplayed()
        composeTestRule.onNodeWithText("Test title Article 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test title Article 2").assertIsDisplayed()
    }

    @Test
    fun displayError() {
        val errorMessage = "Erreur"
        val errorState = MutableLiveData<HeadlinesUiState>(HeadlinesUiState.Error(errorMessage))
        every { mockViewModel.headlines } returns errorState

        composeTestRule.setContent {
            HeadlinesScreen(
                headlineViewModel = mockViewModel,
                sharedViewModel = mockSharedViewModel,
                onItemClick = { }
            )
        }

        composeTestRule.onNode(hasTestTag("headlines_error")).assertIsDisplayed()
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun invokeItemClickCorrectly() {
        val successState =
            MutableLiveData<HeadlinesUiState>(HeadlinesUiState.Success(testHeadlines))
        every { mockViewModel.headlines } returns successState

        val onItemClick: () -> Unit = {}

        composeTestRule.setContent {
            HeadlinesScreen(
                headlineViewModel = mockViewModel,
                sharedViewModel = mockSharedViewModel,
                onItemClick = onItemClick
            )
        }

        composeTestRule.onNodeWithText("Test title Article 1").performClick()
        verify(exactly = 1) { mockSharedViewModel.addArticle(article = testArticles[0]) }

    }
}

