package com.him.sama.spotifycompose.feature.home

import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.him.sama.spotifycompose.common.extension.collectInLaunchedEffectWithLifecycle
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.feature.home.screen.MobileHomeBody
import com.him.sama.spotifycompose.feature.home.screen.TabletHomeBody
import com.him.sama.spotifycompose.feature.home.screen.TelevisionHomeBody
import com.hoc081098.flowext.startWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    viewModel.singleEvent.collectInLaunchedEffectWithLifecycle { event ->
        when (event) {
            is HomeSingleEvent.GetUsersError -> Toast.makeText(
                context,
                "GetUsersError",
                Toast.LENGTH_LONG
            ).show()

            is HomeSingleEvent.Refresh.Failure -> Toast.makeText(
                context,
                "Failure",
                Toast.LENGTH_LONG
            ).show()

            HomeSingleEvent.Refresh.Success -> {}
        }
    }
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val intentChannel = remember { Channel<HomeViewIntent>(Channel.UNLIMITED) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main.immediate) {
            intentChannel
                .consumeAsFlow()
                .startWith(HomeViewIntent.Initial)
                .onEach(viewModel::processIntent)
                .collect()
        }
    }

    val windowSize = rememberWindowSize()
    when (windowSize.width) {
        WindowType.Mobile -> MobileHomeBody(windowSize, viewState)
        WindowType.Television -> TelevisionHomeBody(windowSize, viewState)
        WindowType.Tablet -> TabletHomeBody(windowSize, viewState)
        WindowType.Automotive -> Text(
            text = "Automotive",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
    }
}
