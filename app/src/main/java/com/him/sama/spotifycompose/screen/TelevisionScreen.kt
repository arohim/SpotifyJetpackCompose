package com.him.sama.spotifycompose.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.him.sama.spotifycompose.navigation.BottomBar

@Composable
fun TelevisionScreen(
    selectedDestination: MutableState<BottomBar>,
    navController: NavHostController
) {
    Text("TelevisionScreen")
}