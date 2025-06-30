package com.jo.news.network

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeadlineServiceTest {

    private lateinit var server: MockWebServer
    private lateinit var service: HeadlineService

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
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getHeadlinesWithCorrectParamsAndQuery() = runTest {
        val mockResponse = MockResponse()
            .setBody("{}")
            .setResponseCode(200)

        server.enqueue(mockResponse)
        service.getHeadlines(country = countryCode, apiKey = API_KEY_TEST)

        val request = server.takeRequest()
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path).isEqualTo("/v2/top-headlines?country=$countryCode&apiKey=${API_KEY_TEST}")
    }

    @Test
    fun getHeadlinesReturn200() = runTest {
        val responseBody = """
            {
                "status": "ok",
                "totalResults": 1,
                "articles": [
                    {
                        "source": {"id": "id", "name": "name"},
                        "author": "Jo",
                        "title": "Title",
                        "description": "Test Description",
                        "url": "https://news.com/test-url",
                        "urlToImage": "https://example.com/test-image.jpg",
                        "publishedAt": "2025-06-24T10:00:00Z",
                        "content": "Test content"
                    }
                ]
            }
        """
        val mockResponse = MockResponse()
            .setBody(responseBody)
            .setResponseCode(200)

        server.enqueue(mockResponse)

        val headlines = service.getHeadlines(country = "fr", apiKey = API_KEY_TEST)

        assertThat(headlines).isNotNull()
        assertThat(headlines.status).isEqualTo("ok")
        assertThat(headlines.totalResults).isEqualTo(1)
        assertThat(headlines.articles).hasSize(1)

        val article = headlines.articles?.first()
        assertThat(article).isNotNull()
        assertThat(article?.source?.name).isEqualTo("name")
        assertThat(article?.title).isEqualTo("Title")
        assertThat(article?.description).isEqualTo("Test Description")
    }
}