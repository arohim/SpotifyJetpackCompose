package com.him.sama.spotifycompose.yourlibrary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.ui.R
import com.him.sama.spotifycompose.common.ui.theme.AppTheme

@Composable
fun YourLibraryScreen() {
    Body()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Body() {
    Scaffold(
        containerColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .graphicsLayer(clip = false)
                .clip(RectangleShape)
        ) {
            stickyHeader { Header() }
            item { Filter() }
            items(30, itemContent = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.LightGray)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Stress Relief",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "Playlist",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            })
        }
    }
}

@Composable
fun Filter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Recents",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
private fun Header() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Black,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(top = 32.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Your Library",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.abc_ic_search_api_material),
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_circle_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TagItem("Playlists")
                Spacer(modifier = Modifier.width(8.dp))
                TagItem("Podcasts")
                Spacer(modifier = Modifier.width(8.dp))
                TagItem("Albums")
                Spacer(modifier = Modifier.width(8.dp))
                TagItem("Artists")
            }
        }
    }
}

@Composable
private fun TagItem(text: String) {
    Text(
        modifier = Modifier
            .background(Color(0xff2a2a2a), CircleShape)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Preview
@Composable
private fun PreviewBody() {
    AppTheme {
        Body()
    }
}
