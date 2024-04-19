@file:OptIn(ExperimentalLayoutApi::class)

package com.him.sama.spotifycompose.yourlibrary.screen

import SpotifyCircleLoading
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.him.sama.spotifycompose.yourlibrary.YourLibraryItemModel
import com.him.sama.spotifycompose.yourlibrary.YourLibraryModel
import com.him.sama.spotifycompose.yourlibrary.YourLibraryViewState
import com.him.sama.spotifycompose.yourlibrary.component.Filter
import com.him.sama.spotifycompose.yourlibrary.component.MobileHeader
import com.him.sama.spotifycompose.yourlibrary.component.YourLibraryGridItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabletYourLibraryBody(windowSize: WindowSize, viewState: YourLibraryViewState) {
    Scaffold(
        containerColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .graphicsLayer(clip = false)
                .clip(RectangleShape)
        ) {
            stickyHeader { MobileHeader() }
            item {
                Filter(windowSize = windowSize)
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
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
                    GridView(viewState)
                }
            }
        }
    }
}


@Composable
private fun GridView(viewState: YourLibraryViewState) {
    val widthFraction = 1.0f / 5
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        maxItemsInEachRow = 5,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        viewState.data.items.forEachIndexed { i, item ->
            YourLibraryGridItem(
                modifier = Modifier.fillMaxWidth(widthFraction - 0.02f),
                image = item.image,
                title = item.title
            )
        }
    }
}

@TabletPreview
@Composable
private fun PreviewBody() {
    AppTheme {
        var viewState = YourLibraryViewState.initial()
        viewState = viewState.copy(
            isLoading = false,
            data = YourLibraryModel(
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
        TabletYourLibraryBody(rememberWindowSize(), viewState)
    }
}