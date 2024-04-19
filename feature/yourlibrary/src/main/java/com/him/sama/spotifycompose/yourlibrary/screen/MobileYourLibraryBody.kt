@file:OptIn(ExperimentalLayoutApi::class)

package com.him.sama.spotifycompose.yourlibrary.screen

import SpotifyCircleLoading
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.preview.MobilePreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.yourlibrary.YourLibraryItemModel
import com.him.sama.spotifycompose.yourlibrary.YourLibraryModel
import com.him.sama.spotifycompose.yourlibrary.YourLibraryViewState
import com.him.sama.spotifycompose.yourlibrary.component.Filter
import com.him.sama.spotifycompose.yourlibrary.component.MobileHeader
import com.him.sama.spotifycompose.yourlibrary.component.YourLibraryGridItem
import com.him.sama.spotifycompose.yourlibrary.component.YourLibraryItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MobileYourLibraryBody(windowSize: WindowSize, viewState: YourLibraryViewState) {
    var showGrid by remember {
        mutableStateOf(false)
    }
    Scaffold(
        containerColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .graphicsLayer(clip = false)
                .clip(RectangleShape)
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black),
                ) {
                    MobileHeader()
                    Filter(windowSize = windowSize, showGrid) {
                        showGrid = !showGrid
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
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
                if (showGrid) {
                    item {
                        GridView(viewState)
                    }
                } else {
                    items(viewState.data.items, itemContent = {
                        YourLibraryItem(it)
                        Spacer(modifier = Modifier.height(16.dp))
                    })
                }
            }
        }
    }
}

@Composable
private fun GridView(viewState: YourLibraryViewState) {
    val widthFraction = 1.0f / 3
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        maxItemsInEachRow = 3,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        viewState.data.items.forEachIndexed { i, item ->
            YourLibraryGridItem(
                modifier = Modifier.fillMaxWidth(widthFraction - 0.04209f),
                image = item.image,
                title = item.title
            )
        }
    }
}


@MobilePreview
@Composable
private fun PreviewBody() {
    AppTheme {
        var viewState = YourLibraryViewState.initial()
        viewState = viewState.copy(
            isLoading = false,
            data = YourLibraryModel(
                title = "Playlist",
                items = listOf(
                    YourLibraryItemModel(
                        categoryHierarchy = "playlist * playlist",
                        image = "https://i.scdn.co/image/ab67706f00000002f46ec304be9222df641117a2",
                        title = "Title"
                    ),
                    YourLibraryItemModel(
                        categoryHierarchy = "playlist * playlist",
                        image = "https://i.scdn.co/image/ab67706f00000002f46ec304be9222df641117a2",
                        title = "Title"
                    ),
                    YourLibraryItemModel(
                        categoryHierarchy = "playlist * playlist",
                        image = "https://i.scdn.co/image/ab67706f00000002f46ec304be9222df641117a2",
                        title = "Title"
                    ),
                    YourLibraryItemModel(
                        categoryHierarchy = "playlist * playlist",
                        image = "https://i.scdn.co/image/ab67706f00000002f46ec304be9222df641117a2",
                        title = "Title"
                    ),
                    YourLibraryItemModel(
                        categoryHierarchy = "playlist * playlist",
                        image = "https://i.scdn.co/image/ab67706f00000002f46ec304be9222df641117a2",
                        title = "Title"
                    ),
                    YourLibraryItemModel(
                        categoryHierarchy = "playlist * playlist",
                        image = "https://i.scdn.co/image/ab67706f00000002f46ec304be9222df641117a2",
                        title = "Title"
                    ),
                    YourLibraryItemModel(
                        categoryHierarchy = "playlist * playlist",
                        image = "https://i.scdn.co/image/ab67706f00000002f46ec304be9222df641117a2",
                        title = "Title"
                    )
                )
            )
        )
        MobileYourLibraryBody(rememberWindowSize(), viewState)
    }
}