package com.him.sama.spotifycompose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.him.sama.spotifycompose.BottomNavigation
import com.him.sama.spotifycompose.common.component.MobileMusicPlayer
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.navigation.AppNavHost
import com.him.sama.spotifycompose.navigation.BottomBar

@Composable
fun MobileScreen(
    windowSize: WindowSize,
    selectedDestination: MutableState<BottomBar>,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (windowSize.width == WindowType.Mobile) {
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
                    MobileMusicPlayer()
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
                .background(Color.Black)
                .padding(it),
            navController = navController,
            startDestination = selectedDestination.value.route
        )
    }
}