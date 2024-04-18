package com.him.sama.spotifycompose.search.screen

import SpotifyCircleLoading
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.preview.TabletPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.search.SearchViewState
import com.him.sama.spotifycompose.search.component.CategoryList
import com.him.sama.spotifycompose.search.component.Header
import com.him.sama.spotifycompose.search.component.ShortVideoList
import com.him.sama.spotifycompose.search.component.WhiteSearchBox

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun TabletSearchBody(windowSize: WindowSize, viewState: SearchViewState) {
    Scaffold(
        containerColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 120.dp)
                .graphicsLayer(clip = false)
                .clip(RectangleShape)
        ) {
            item { Header() }
            stickyHeader { WhiteSearchBox() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            if (viewState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(400.dp)
                    ) {
                        SpotifyCircleLoading(modifier = Modifier.align(Alignment.Center))
                    }
                }
            } else {
                item {
                    ShortVideoList(windowSize, viewState.data.story)
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    CategoryList(windowSize, viewState.data.categories)
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@TabletPreview
@Composable
private fun PreviewSearchScreen() {
    AppTheme {
        TabletSearchBody(rememberWindowSize(), SearchViewState.initial())
    }
}