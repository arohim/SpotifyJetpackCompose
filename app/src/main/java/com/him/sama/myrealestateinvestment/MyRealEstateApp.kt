package com.him.sama.myrealestateinvestment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.him.sama.myrealestateinvestment.ui.navigation.AppNavHost
import com.him.sama.myrealestateinvestment.ui.theme.BottomBar
import com.him.sama.myrealestateinvestment.ui.theme.MyRealEstateInvestmentTheme

@Composable
fun MyRealEstateApp() {
    val selectedDestination = remember { mutableStateOf(BottomBar.HOME) }
    val navController = rememberNavController()

    MyRealEstateInvestmentTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigation(selectedDestination)
            }
        ) {
            AppNavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = selectedDestination.value.route,
                paddingValues = it
            )
        }
    }
}
