package com.jo.news.network

import com.jo.news.data.model.Headlines
import com.jo.news.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface HeadlineService {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Headlines
}