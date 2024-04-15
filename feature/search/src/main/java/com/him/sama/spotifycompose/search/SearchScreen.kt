package com.him.sama.spotifycompose.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.search.screen.MobileSearchBody
import com.him.sama.spotifycompose.search.screen.TabletSearchBody
import com.him.sama.spotifycompose.search.screen.TelevisionSearchBody
import com.hoc081098.flowext.startWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val intentChannel = remember { Channel<SearchViewIntent>(Channel.UNLIMITED) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main.immediate) {
            intentChannel
                .consumeAsFlow()
                .startWith(SearchViewIntent.Initial)
                .onEach(viewModel::processIntent)
                .collect()
        }
    }

    val windowSize = rememberWindowSize()
    when (windowSize.width) {
        WindowType.Mobile -> MobileSearchBody(windowSize, viewState)
        WindowType.Television -> TelevisionSearchBody(windowSize, viewState)
        WindowType.Tablet -> TabletSearchBody(windowSize = windowSize, viewState)
        WindowType.Automotive -> Text("Automotive")
    }
}