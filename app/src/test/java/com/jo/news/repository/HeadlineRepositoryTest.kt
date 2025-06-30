package com.jo.news.repository

import com.google.common.truth.Truth.assertThat
import com.jo.news.data.model.Article
import com.jo.news.data.model.Headlines
import com.jo.news.data.model.Source
import com.jo.news.network.HeadlineService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeadlineRepositoryTest {

    private lateinit var server: MockWebServer
    private lateinit var service: HeadlineService
    private lateinit var repository: HeadlineRepository

    private val API_KEY_TEST = "a52483ff219e4d40b1e1729dfe2c6e6f"
    private val countryCode = "us"

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()

        service = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeadlineService::class.java)

        repository = HeadlineRepository(service)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getHeadlinesReturnGoodHeadlines() = runTest {
        val expectedResponse = """
            {
                "status": "ok",
                "totalResults": 1,
                "articles": [
                    {
                        "source": {"id": "id", "name": "name"},
                        "author": "Jo",
                        "title": "Title",
                        "description": "Test Description",
                        "url": "http://news.com/test-url",
                        "urlToImage": "http://example.com/test-image.jpg",
                        "publishedAt": "2025-06-24T10:00:00Z",
                        "content": "Test content"
                    }
                ]
            }
        """

        val mockResponse = MockResponse()
            .setBody(expectedResponse)
            .setResponseCode(200)
        server.enqueue(mockResponse)

        val headlines = repository.getHeadlines(country = countryCode)
        val recordedRequest = server.takeRequest()
        assertThat(recordedRequest.method).isEqualTo("GET")
        assertThat(recordedRequest.path).isEqualTo("/v2/top-headlines?country=$countryCode&apiKey=$API_KEY_TEST")

        val expectedArticle = Article(
            source = Source(id = "id", name = "name"),
            author = "Jo",
            title = "Title",
            description = "Test Description",
            url = "http://news.com/test-url",
            urlToImage = "http://example.com/test-image.jpg",
            publishedAt = "2025-06-24T10:00:00Z",
            content = "Test content"
        )
        val expectedResult = Headlines(
            status = "ok",
            totalResults = 1,
            articles = listOf(expectedArticle)
        )
        assertThat(headlines).isEqualTo(expectedResult)
    }
}