package com.him.sama.spotifycompose.feature.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.feature.home.screen.MobileHomeBody
import com.him.sama.spotifycompose.feature.home.screen.TabletHomeBody
import com.him.sama.spotifycompose.feature.home.screen.TelevisionHomeBody

@Composable
fun HomeScreen() {
    val windowSize = rememberWindowSize()
    when (windowSize.width) {
        WindowType.Mobile -> MobileHomeBody(windowSize)
        WindowType.Television -> TelevisionHomeBody(windowSize)
        WindowType.Tablet -> TabletHomeBody(windowSize)
        WindowType.Automotive -> Text(
            text = "Automotive",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
    }
}
