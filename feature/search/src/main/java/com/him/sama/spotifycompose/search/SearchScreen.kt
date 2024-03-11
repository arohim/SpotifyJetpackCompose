package com.him.sama.spotifycompose.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.search.screen.MobileSearchBody
import com.him.sama.spotifycompose.search.screen.TabletSearchBody
import com.him.sama.spotifycompose.search.screen.TelevisionSearchBody

@Composable
fun SearchScreen() {
    val windowSize = rememberWindowSize()
    when (windowSize.width) {
        WindowType.Mobile -> MobileSearchBody(windowSize)
        WindowType.Television -> TelevisionSearchBody(windowSize)
        WindowType.Tablet -> TabletSearchBody(windowSize = windowSize)
        WindowType.Automotive -> Text("Automotive")
    }
}