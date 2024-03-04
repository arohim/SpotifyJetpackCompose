package com.him.sama.spotifycompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.navigation.BottomBar
import com.him.sama.spotifycompose.screen.AutomotiveScreen
import com.him.sama.spotifycompose.screen.MobileScreen
import com.him.sama.spotifycompose.screen.TabletScreen

@Composable
fun MyApp() {
    val selectedDestination = remember { mutableStateOf(BottomBar.HOME) }
    val navController = rememberNavController()
    val windowSize = rememberWindowSize()

    AppTheme {
        when (windowSize.width) {
            WindowType.Mobile -> MobileScreen(windowSize, selectedDestination, navController)
            WindowType.Tablet -> TabletScreen(selectedDestination, navController)
            else -> AutomotiveScreen(selectedDestination, navController)
        }
    }
}


