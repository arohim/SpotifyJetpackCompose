package com.him.sama.spotifycompose.yourlibrary

import TelevisionYourLibraryBody
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.yourlibrary.screen.MobileYourLibraryBody
import com.him.sama.spotifycompose.yourlibrary.screen.TabletYourLibraryBody

@Composable
fun YourLibraryScreen() {
    val windowSize = rememberWindowSize()
    when (windowSize.width) {
        WindowType.Mobile -> MobileYourLibraryBody()
        WindowType.Television -> TelevisionYourLibraryBody()
        WindowType.Tablet -> TabletYourLibraryBody()
        WindowType.Automotive -> Text(text = "Automotive")
    }
}
