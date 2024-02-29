package com.him.sama.spotifycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.him.sama.spotifycompose.common.component.MusicPlayer
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.navigation.AppNavHost
import com.him.sama.spotifycompose.navigation.BottomBar

@Composable
fun MyApp() {
    val selectedDestination = remember { mutableStateOf(BottomBar.HOME) }
    val navController = rememberNavController()
    val windowSize = rememberWindowSize()

    AppTheme {
        MobileScreen(windowSize, selectedDestination, navController)
    }
}

@Composable
private fun MobileScreen(
    windowSize: WindowSize,
    selectedDestination: MutableState<BottomBar>,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (windowSize.width == WindowType.Compact) {
                Column(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Black
                                )
                            )
                        )
                        .padding(top = 20.dp)
                ) {
                    MusicPlayer()
                    BottomNavigation(selectedDestination.value, onClick = {
                        selectedDestination.value = it
                    })
                }
            }
        }
    ) {
        AppNavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            navController = navController,
            startDestination = selectedDestination.value.route,
            paddingValues = it
        )
    }
}
