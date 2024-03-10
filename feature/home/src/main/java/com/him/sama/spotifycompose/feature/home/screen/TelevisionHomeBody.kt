package com.him.sama.spotifycompose.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.preview.TelevisionPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.background_color
import com.him.sama.spotifycompose.feature.home.component.HighlightedAlbum
import com.him.sama.spotifycompose.feature.home.component.PlayingBanner
import com.him.sama.spotifycompose.feature.home.component.RecommendationSection

@Composable
internal fun TelevisionHomeBody(windowSize: WindowSize) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color),
    ) {
        if (windowSize.width == WindowType.Television) {
            Spacer(modifier = Modifier.height(24.dp))
            PlayingBanner()
            Spacer(modifier = Modifier.height(32.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(clip = false)
                .padding(top = 24.dp, start = 48.dp, end = 48.dp, bottom = 0.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            RecommendationSection(windowSize)
            Spacer(modifier = Modifier.height(26.dp))
            HighlightedAlbum()
            HighlightedAlbum()
            HighlightedAlbum()
            Spacer(modifier = Modifier.height(56.dp))
        }
    }
}

@TelevisionPreview
@Composable
private fun PreviewBody() {
    AppTheme {
        TelevisionHomeBody(rememberWindowSize())
    }
}