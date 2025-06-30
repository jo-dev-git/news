package com.jo.news.navigation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.jo.news.HiltTestActivity
import com.jo.news.data.HeadlinesUiState
import com.jo.news.data.model.Article
import com.jo.news.data.model.Headlines
import com.jo.news.data.model.Source
import com.jo.news.ui.viewmodel.HeadlineViewModel
import com.jo.news.ui.viewmodel.SharedViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HeadlinesNavigationTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private lateinit var navController: TestNavHostController
    private lateinit var mockSharedViewModel: SharedViewModel
    private lateinit var mockHeadlineViewModel: HeadlineViewModel

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
    private val testHeadlines = Headlines("ok", 1, listOf(article))

    @Before
    fun setup() {
        hiltRule.inject()

        mockSharedViewModel = mockk(relaxed = true)
        mockSharedViewModel.addArticle(article)

        mockHeadlineViewModel = mockk(relaxed = true)
        val successState =
            MutableLiveData<HeadlinesUiState>(HeadlinesUiState.Success(testHeadlines))
        every { mockHeadlineViewModel.headlines } returns successState

        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.navigatorProvider.addNavigator(ComposeNavigator())

        composeTestRule.setContent {
            HeadlinesNavigation(
                navController = navController,
                sharedViewModel = mockSharedViewModel,
                headlineViewModelFactory = { mockHeadlineViewModel }
            )
        }
    }

    @Test
    fun verifyStartDestination() {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(Screens.HeadlinesListScreen.name, currentRoute)
    }

    @Test
    fun navigatesToArticleScreen() {
        composeTestRule
            .onNodeWithTag("headlines_item")
            .performClick()

        val newRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(Screens.ArticleScreen.name, newRoute)
    }

    @Test
    fun navigateAndGoBack() {
        composeTestRule
            .onNodeWithTag("headlines_item")
            .performClick()

        var currentRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(Screens.ArticleScreen.name, currentRoute)

        composeTestRule
            .onNodeWithTag("Back")
            .performClick()

        currentRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(Screens.HeadlinesListScreen.name, currentRoute)
    }
}