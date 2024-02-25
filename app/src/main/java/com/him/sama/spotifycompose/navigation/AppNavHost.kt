package com.him.sama.spotifycompose.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.him.sama.spotifycompose.feature.home.HomeScreen
import com.him.sama.spotifycompose.search.SearchScreen
import com.him.sama.spotifycompose.yourlibrary.YourLibraryScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(HOME_ROUTE) {
            HomeScreen()
        }
        composable(PASSIVE_INCOME_ROUTE) {
            SearchScreen()
        }
        composable(SETTING_ROUTE) {
            YourLibraryScreen()
        }
    }
}