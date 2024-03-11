package com.him.sama.spotifycompose.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.R
import com.him.sama.spotifycompose.common.ui.preview.TelevisionPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.background_color
import com.him.sama.spotifycompose.common.ui.theme.title_text_color
import com.him.sama.spotifycompose.search.component.BlackSearchBox

@Composable
fun TelevisionSearchBody(windowSize: WindowSize) {
    Scaffold(
        containerColor = background_color
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 80.dp)
                .padding(top = 24.dp)
                .graphicsLayer(clip = false)
                .clip(RectangleShape)
        ) {
            BlackSearchBox()
            Spacer(modifier = Modifier.height(32.dp))
            TelevisionSearchCategory(windowSize)
            Spacer(modifier = Modifier.height(24.dp))
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = GridCells.Fixed(5),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(50) {
                    TelevisionSearchCategoryItem()
                }
            }
        }
    }
}

@Composable
private fun TelevisionSearchCategoryItem() {
    Box(
        modifier = Modifier
            .size(140.dp)
            .clickable(onClick = {})
            .background(Color.Black)
            .onFocusChanged {

            }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.baseline_grid_view_24),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Music",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun TelevisionSearchCategory(windowSize: WindowSize) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Genres & Moods",
            style = MaterialTheme.typography.titleLarge,
            color = title_text_color
        )
    }
}

@TelevisionPreview
@Composable
private fun PreviewSearchScreen() {
    AppTheme {
        TelevisionSearchBody(rememberWindowSize())
    }
}