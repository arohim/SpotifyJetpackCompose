package com.him.sama.myrealestateinvestment.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            Text(text = "HOME_ROUTE")
        }
        composable(PASSIVE_INCOME_ROUTE) {
            Text(text = "PASSIVE_INCOME_ROUTE")
        }
        composable(SETTING_ROUTE) {
            Text(text = "SETTING_ROUTE")
        }
    }
}