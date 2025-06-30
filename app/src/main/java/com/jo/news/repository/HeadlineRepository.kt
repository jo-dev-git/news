package com.jo.news.repository

import com.jo.news.data.model.Headlines
import com.jo.news.network.HeadlineService
import javax.inject.Inject

class HeadlineRepository @Inject constructor(private val headlineService: HeadlineService) {
    suspend fun getHeadlines(country: String): Headlines {
        return headlineService.getHeadlines(country)
    }
}
