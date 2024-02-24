package com.him.sama.myrealestateinvestment.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.him.sama.myrealestateinvestment.common.ui.theme.SpotifyJetpackComposeTheme

@Composable
fun HomeScreen() {
    Body()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun Body() {
    Scaffold {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .graphicsLayer(clip = false),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(top = 24.dp, bottom = 56.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Header()
            }
            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(8) {
                RecommendationItem()
            }
            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(26.dp))
            }
            item(span = { GridItemSpan(2) }) {
                HighlightedAlbum()
            }
            item(span = { GridItemSpan(2) }) {
                HighlightedAlbum()
            }
            item(span = { GridItemSpan(2) }) {
                HighlightedAlbum()
            }
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
            items(5) {
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
fun RecommendationItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
                    Color(0xFF4CAF50)
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

@Preview
@Composable
fun PreviewBody() {
    SpotifyJetpackComposeTheme {
        Body()
    }
}
