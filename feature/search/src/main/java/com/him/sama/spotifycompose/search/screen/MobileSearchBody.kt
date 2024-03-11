package com.him.sama.spotifycompose.search.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.him.sama.spotifycompose.search.component.CategoryList
import com.him.sama.spotifycompose.search.component.Header
import com.him.sama.spotifycompose.search.component.WhiteSearchBox
import com.him.sama.spotifycompose.search.component.ShortVideoList

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MobileSearchBody(windowSize: WindowSize) {
    Scaffold(
        containerColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(bottom = 56.dp)
                .graphicsLayer(clip = false)
                .clip(RectangleShape)
        ) {
            item { Header() }
            stickyHeader { WhiteSearchBox() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                ShortVideoList()
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                CategoryList(windowSize)
            }
        }
    }
}

@MobilePreview
@Composable
private fun PreviewSearchScreen() {
    AppTheme {
        MobileSearchBody(rememberWindowSize())
    }
}