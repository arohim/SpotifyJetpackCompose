package com.him.sama.spotifycompose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.him.sama.spotifycompose.common.ui.preview.TabletPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.component.LeftMenu
import com.him.sama.spotifycompose.navigation.AppNavHost
import com.him.sama.spotifycompose.navigation.BottomBar

@Composable
fun TabletScreen(
    selectedDestination: MutableState<BottomBar>,
    navController: NavHostController,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(it),
        ) {
            LeftMenu(
                modifier = Modifier.fillMaxWidth(0.20f),
                selectedDestination = selectedDestination,
                onMenuClick = {
                    selectedDestination.value = it
                }
            )
            AppNavHost(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(it),
                navController = navController,
                startDestination = selectedDestination.value.route
            )
        }
    }
}

@TabletPreview
@Composable
private fun Preview() {
    AppTheme {
        TabletScreen(
            remember { mutableStateOf(BottomBar.HOME) },
            rememberNavController()
        )
    }
}