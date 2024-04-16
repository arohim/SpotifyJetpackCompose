package com.him.sama.spotifycompose.yourlibrary

import TelevisionYourLibraryBody
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.yourlibrary.screen.MobileYourLibraryBody
import com.him.sama.spotifycompose.yourlibrary.screen.TabletYourLibraryBody
import com.hoc081098.flowext.startWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

@Composable
fun YourLibraryScreen(
    viewModel: YourLibraryViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val intentChannel = remember { Channel<YourLibraryViewIntent>(Channel.UNLIMITED) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main.immediate) {
            intentChannel
                .consumeAsFlow()
                .startWith(YourLibraryViewIntent.Initial)
                .onEach(viewModel::processIntent)
                .collect()
        }
    }

    val windowSize = rememberWindowSize()
    when (windowSize.width) {
        WindowType.Mobile -> MobileYourLibraryBody(viewState)
        WindowType.Television -> TelevisionYourLibraryBody(viewState)
        WindowType.Tablet -> TabletYourLibraryBody(viewState)
        WindowType.Automotive -> Text(text = "Automotive")
    }
}
