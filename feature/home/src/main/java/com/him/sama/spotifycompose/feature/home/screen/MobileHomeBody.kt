package com.him.sama.spotifycompose.feature.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MobileHomeBody(windowSize: WindowSize) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)
            .graphicsLayer(clip = false)
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 0.dp),
    ) {
        stickyHeader { Header() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { RecommendationSection(windowSize) }
        item { Spacer(modifier = Modifier.height(26.dp)) }
        item { HighlightedAlbum() }
        item { HighlightedAlbum() }
        item { HighlightedAlbum() }
        item { Spacer(modifier = Modifier.height(56.dp)) }
    }
}

@MobilePreview
@Composable
private fun PreviewBody() {
    AppTheme {
        MobileHomeBody(rememberWindowSize())
    }
}