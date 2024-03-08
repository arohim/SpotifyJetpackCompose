package com.him.sama.spotifycompose.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.preview.AutomotivePreview
import com.him.sama.spotifycompose.common.ui.preview.MobilePreview
import com.him.sama.spotifycompose.common.ui.preview.TabletPreview
import com.him.sama.spotifycompose.common.ui.preview.TelevisionPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.tertiary_background

@Composable
fun HomeScreen() {
    Body()
}

@Composable
private fun Body() {
    val windowSize = rememberWindowSize()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(clip = false)
            .background(color = Color.Black)
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

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RecommendationSection(windowSize: WindowSize) {
    val maxItemsInEachRow = if (windowSize.width == WindowType.Mobile) {
        2
    } else {
        3
    }
    val widthFraction = 1.0f / maxItemsInEachRow

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        maxItemsInEachRow = maxItemsInEachRow,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(12) {
            RecommendationItem(widthFraction = widthFraction)
        }
    }
}

@Composable
fun HighlightedAlbum() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(
                        color = Color.LightGray,
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = "More like",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Alan Walker",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(clip = false),
            contentPadding = PaddingValues(top = 24.dp, bottom = 56.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                AlbumItem()
            }
        }
    }
}

@Composable
private fun AlbumItem() {
    Column(
        modifier = Modifier
            .width(150.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clip(shape = RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Justin Bieber, Bruno Mars,Coldplay, Dua Lipa, Imagikjkjkjkj",
            style = MaterialTheme.typography.labelSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            color = Color.LightGray
        )
    }
}

@Composable
fun RecommendationItem(widthFraction: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth(widthFraction - 0.011f)
            .height(56.dp)
            .background(
                color = Color.LightGray.copy(alpha = 0.4f),
                shape = RoundedCornerShape(4.dp)
            )
            .clip(shape = RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(56.dp)
                .background(color = Color.LightGray)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Running test",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color.LightGray,
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Tags()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Tags() {
    TagItem("All", true)
    Spacer(modifier = Modifier.width(8.dp))
    TagItem("Music", false)
    Spacer(modifier = Modifier.width(8.dp))
    TagItem("Podcasts", false)
}

@Composable
fun TagItem(title: String, isSelected: Boolean) {
    Text(
        modifier = Modifier
            .background(
                color = if (isSelected)
                    tertiary_background
                else
                    Color(0xFF4B524C),
                shape = CircleShape
            )
            .padding(horizontal = 22.dp)
            .padding(vertical = 10.dp),
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = if (isSelected) Color.Black else Color.White
    )
}

@MobilePreview
@TabletPreview
@TelevisionPreview
@AutomotivePreview
@Composable
fun PreviewBody() {
    AppTheme {
        Body()
    }
}
