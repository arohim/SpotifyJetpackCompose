package com.him.sama.spotifycompose.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.preview.AutomotivePreview
import com.him.sama.spotifycompose.common.ui.preview.MobilePreview
import com.him.sama.spotifycompose.common.ui.preview.TabletPreview
import com.him.sama.spotifycompose.common.ui.preview.TelevisionPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    val windowSize = rememberWindowSize()
    val horizontalPadding = if (windowSize.width == WindowType.Mobile) {
        0.dp
    } else {
        120.dp
    }
    Scaffold(
        containerColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = horizontalPadding)
                .padding(bottom = 56.dp)
                .graphicsLayer(clip = false)
                .clip(RectangleShape)
        ) {
            item { Header() }
            stickyHeader { SearchBox() }
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

@Composable
fun ShortVideoList() {
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(115.dp)
                    .height(200.dp)
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .width(115.dp)
                    .height(200.dp)
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .width(115.dp)
                    .height(200.dp)
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    )
            )
        }
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun CategoryList(windowSize: WindowSize) {
    val colors = listOf(
        Color(0xffc4147c),
        Color(0xff8300e7),
        Color(0xff006450),
        Color(0xff93113e)
    )
    val column = if (windowSize.width == WindowType.Mobile) {
        2
    } else if (windowSize.width == WindowType.Tablet) {
        4
    } else {
        6
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = "Browse all",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            modifier = Modifier,
            maxItemsInEachRow = column,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(50) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .background(
                            colors[Random.nextInt(3)],
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Pod cast",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(70.dp)
                            .background(Color.LightGray)
                            .clip(RoundedCornerShape(bottomEnd = 8.dp))
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchBox() {
    TextField(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        shape = RoundedCornerShape(4.dp),
        placeholder = {
            Text(
                text = "What do you want to listen to?",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = com.him.sama.spotifycompose.common.ui.R.drawable.encore_icon_search_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        value = "",
        onValueChange = {},
    )
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Search",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = com.him.sama.spotifycompose.common.ui.R.drawable.baseline_photo_camera_24),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
private fun Body(modifier: Modifier) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .graphicsLayer(clip = false),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 24.dp, bottom = 56.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
//            item(span = { GridItemSpan(2) }) {
//                Header()
//            }
//            item(span = { GridItemSpan(2) }) {
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//            items(8) {
//                RecommendationItem()
//            }
//            item(span = { GridItemSpan(2) }) {
//                Spacer(modifier = Modifier.height(26.dp))
//            }
//            item(span = { GridItemSpan(2) }) {
//                HighlightedAlbum()
//            }
//            item(span = { GridItemSpan(2) }) {
//                HighlightedAlbum()
//            }
//            item(span = { GridItemSpan(2) }) {
//                HighlightedAlbum()
//            }
    }
}

@MobilePreview
@TelevisionPreview
@TabletPreview
@AutomotivePreview
@Composable
fun PreviewSearchScreen() {
    AppTheme {
        SearchScreen()
    }
}