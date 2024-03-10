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
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.preview.MobilePreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.background_color
import com.him.sama.spotifycompose.feature.home.component.Header
import com.him.sama.spotifycompose.feature.home.component.HighlightedAlbum
import com.him.sama.spotifycompose.feature.home.component.RecommendationSection

@Composable
internal fun MobileHomeBody(windowSize: WindowSize) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)
            .graphicsLayer(clip = false)
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        RecommendationSection(windowSize)
        Spacer(modifier = Modifier.height(26.dp))
        HighlightedAlbum()
        HighlightedAlbum()
        HighlightedAlbum()
        Spacer(modifier = Modifier.height(56.dp))
    }
}

@MobilePreview
@Composable
private fun PreviewBody() {
    AppTheme {
        MobileHomeBody(rememberWindowSize())
    }
}