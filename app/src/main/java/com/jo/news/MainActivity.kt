package com.jo.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.jo.news.navigation.HeadlinesNavigation
import com.jo.news.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                HeadlinesApp()
            }
        }
    }
}

@Composable
fun HeadlinesApp() {
    HeadlinesNavigation()
}