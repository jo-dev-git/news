package com.jo.news.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.jo.news.TestCoroutineRule
import com.jo.news.data.HeadlinesUiState
import com.jo.news.data.model.Article
import com.jo.news.data.model.Headlines
import com.jo.news.data.model.Source
import com.jo.news.repository.HeadlineRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HeadlineViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var mockHeadlineRepository: HeadlineRepository

    private lateinit var viewModel: HeadlineViewModel

    private val country = "us"

    @Before
    fun setUp() {
        viewModel = HeadlineViewModel(mockHeadlineRepository)
    }

    @Test
    fun getHeadlinesReturnSuccessState() = runTest(testCoroutineRule.testDispatcher) {
        val mockArticle = Article(
            Source("id", "name"),
            "author",
            "title",
            "description",
            "url",
            "urlToImage",
            "date",
            "content"
        )
        val expectedHeadlines = Headlines("ok", 1, listOf(mockArticle))
        val expectedUiState = HeadlinesUiState.Success(expectedHeadlines)

        `when`(mockHeadlineRepository.getHeadlines(country)).thenReturn(expectedHeadlines)

        viewModel.getHeadlines(country)

        val actualUiState = viewModel.headlines.value
        assertThat(actualUiState).isNotNull()
        assertThat(actualUiState).isInstanceOf(HeadlinesUiState.Success::class.java)
        assertThat(actualUiState).isEqualTo(expectedUiState)

        verify(mockHeadlineRepository).getHeadlines(country)
    }

    @Test
    fun getHeadlinesReturnErrorState() = runTest(testCoroutineRule.testDispatcher) {
        val errorMessage = "Error message"
        val exception = RuntimeException(errorMessage)
        val expectedUiState = HeadlinesUiState.Error("Error: $errorMessage")

        `when`(mockHeadlineRepository.getHeadlines(country)).thenThrow(exception)

        viewModel.getHeadlines(country)

        val actualUiState = viewModel.headlines.value
        assertThat(actualUiState).isNotNull()
        assertThat(actualUiState).isInstanceOf(HeadlinesUiState.Error::class.java)
        assertThat(actualUiState).isEqualTo(expectedUiState)

        verify(mockHeadlineRepository).getHeadlines(country)
    }
}