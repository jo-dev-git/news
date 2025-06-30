package com.jo.news.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jo.news.R
import com.jo.news.data.model.Headlines

@Composable
fun ResultsTotalView(headlines: Headlines) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                color = Color.LightGray,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = pluralStringResource(
                id = R.plurals.results_count,
                count = headlines.totalResults!!,
                headlines.totalResults
            ),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 45.sp
        )
    }
}