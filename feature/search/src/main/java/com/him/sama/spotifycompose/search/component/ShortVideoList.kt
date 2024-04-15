package com.him.sama.spotifycompose.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.search.StoryItemModel

@Composable
fun ShortVideoList(windowSize: WindowSize, story: List<StoryItemModel>) {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableIntStateOf(configuration.screenWidthDp)
    }
    val itemWidth = when (windowSize.width) {
        WindowType.Mobile -> ((screenWidth - (16 * 4)) / 3).dp
        else -> 115.dp
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(clip = false)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Explore your genes",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(story, itemContent = {
                Box(
                    modifier = Modifier,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .width(itemWidth)
                            .height(200.dp)
                            .background(
                                Color.LightGray,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clip(RoundedCornerShape(16.dp)),
                        model = it.image,
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .width(itemWidth)
                            .height(100.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.8f)
                                    )
                                ),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .width(itemWidth)
                            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                        text = it.title,
                        maxLines = 2,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White
                    )
                }
            })
        }
    }
}

@Preview
@Composable
private fun PreviewShortVideoList() {
    AppTheme {
        ShortVideoList(
            WindowSize(width = WindowType.Mobile, height = WindowType.Mobile),
            listOf(
                StoryItemModel(title = "#test test test test", image = ""),
                StoryItemModel(title = "#test", image = ""),
                StoryItemModel(title = "#test", image = "")
            )
        )
    }
}