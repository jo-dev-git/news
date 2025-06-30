package com.jo.news.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jo.news.ui.screens.ArticleScreen
import com.jo.news.ui.screens.HeadlinesScreen
import com.jo.news.ui.viewmodel.HeadlineViewModel
import com.jo.news.ui.viewmodel.SharedViewModel

@Composable
fun HeadlinesNavigation(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: SharedViewModel = viewModel(),
    headlineViewModelFactory: @Composable () -> HeadlineViewModel = { hiltViewModel<HeadlineViewModel>() }
) {
    val headlineViewModel = headlineViewModelFactory()

    NavHost(
        navController = navController,
        startDestination = Screens.HeadlinesListScreen.name
    ) {
        composable(Screens.HeadlinesListScreen.name) {
            HeadlinesScreen(
                headlineViewModel,
                sharedViewModel,
                onItemClick = {
                    navController.navigate(Screens.ArticleScreen.name)
                }
            )
        }
        composable(Screens.ArticleScreen.name) {
            ArticleScreen(sharedViewModel, back = { navController.navigateUp() })
        }
    }
}