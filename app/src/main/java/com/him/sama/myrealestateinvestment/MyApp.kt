package com.him.sama.myrealestateinvestment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.him.sama.myrealestateinvestment.navigation.AppNavHost
import com.him.sama.myrealestateinvestment.navigation.BottomBar
import com.him.sama.myrealestateinvestment.common.ui.theme.SpotifyJetpackCompose

@Composable
fun MyApp() {
    val selectedDestination = remember { mutableStateOf(BottomBar.HOME) }
    val navController = rememberNavController()

    SpotifyJetpackCompose {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                Column(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Black))
                        )
                        .padding(top = 20.dp)
                ) {
                    BottomNavigation(selectedDestination.value, onClick = {
                        selectedDestination.value = it
                    })
                }
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
