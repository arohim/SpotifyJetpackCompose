package com.him.sama.spotifycompose.feature.home.screen

import SpotifyCircleLoading
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.preview.TabletPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.background_color
import com.him.sama.spotifycompose.feature.home.HomeLayoutType
import com.him.sama.spotifycompose.feature.home.HomeViewState
import com.him.sama.spotifycompose.feature.home.component.Header
import com.him.sama.spotifycompose.feature.home.component.HighlightedAlbum
import com.him.sama.spotifycompose.feature.home.component.PickedForYou
import com.him.sama.spotifycompose.feature.home.component.RecommendationSection

@Composable
internal fun TabletHomeBody(windowSize: WindowSize, viewState: HomeViewState) {
    val modifier = if (viewState.isLoading) {
        Modifier
            .fillMaxSize()
            .background(color = background_color)
            .graphicsLayer(clip = false)
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
    } else {
        Modifier
            .fillMaxSize()
            .background(color = background_color)
            .graphicsLayer(clip = false)
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
            .verticalScroll(rememberScrollState())
    }

    Column(
        modifier = modifier
    ) {
        Header()
        if (viewState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SpotifyCircleLoading()
            }
        } else {
            Spacer(modifier = Modifier.height(34.dp))
            viewState.homeItems.forEach {
                Spacer(modifier = Modifier.height(26.dp))
                when (it.layoutType) {
                    HomeLayoutType.GRID -> {
                        RecommendationSection(windowSize, it.items)
                    }

                    HomeLayoutType.ALBUM -> {
                        HighlightedAlbum(it)
                    }

                    HomeLayoutType.PLAY_WIDGET -> {
                        PickedForYou(data = it)
                    }
                }
            }
            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@TabletPreview
@Composable
private fun PreviewBody() {
    AppTheme {
        TabletHomeBody(rememberWindowSize(), HomeViewState.initial())
    }
}