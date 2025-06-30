package com.jo.news.ui.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.jo.news.R
import org.junit.Rule
import org.junit.Test

class HeadlinesErrorViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayMessageError() {
        val testMessage = "Erreur"
        composeTestRule.setContent { HeadlinesErrorView(message = testMessage) }
        composeTestRule.onNodeWithText(testMessage).assertIsDisplayed()
    }

    @Test
    fun displayDefaultMessageError() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val testMessage = context.getString(R.string.error_headlines)

        composeTestRule.setContent { HeadlinesErrorView() }
        composeTestRule.onNodeWithText(testMessage).assertIsDisplayed()
    }
}