package com.him.sama.spotifycompose.search.screen

import SpotifyCircleLoading
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.preview.TelevisionPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.background_color
import com.him.sama.spotifycompose.search.CategoryModel
import com.him.sama.spotifycompose.search.SearchPageModel
import com.him.sama.spotifycompose.search.SearchViewState
import com.him.sama.spotifycompose.search.component.BlackSearchBox

@Composable
fun TelevisionSearchBody(windowSize: WindowSize, viewState: SearchViewState) {
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
            if (viewState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SpotifyCircleLoading(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                TelevisionSearchCategory()
                Spacer(modifier = Modifier.height(24.dp))
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Fixed(5),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(viewState.data.categories) {
                        TelevisionSearchCategoryItem(it)
                    }
                }
            }
        }
    }
}

@Composable
private fun TelevisionSearchCategoryItem(categoryModel: CategoryModel) {
    Box(
        modifier = Modifier
            .size(140.dp)
            .clickable(onClick = {})
            .clip(RoundedCornerShape(8.dp))
            .onFocusChanged {
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(140.dp),
            model = categoryModel.image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = categoryModel.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun TelevisionSearchCategory() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Genres & Moods",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
    }
}

@TelevisionPreview
@Composable
private fun PreviewSearchScreen() {
    AppTheme {
        TelevisionSearchBody(
            rememberWindowSize(),
            SearchViewState.initial().copy(
                data = SearchPageModel(
                    categories = listOf(
                        CategoryModel(
                            title = "Comedy",
                            bgColor = "#af2896",
                            image = "https://i.scdn.co/image/ab6765630000ba8a77d267a5accb8911a92668e1"
                        ),
                        CategoryModel(
                            title = "Comedy",
                            bgColor = "#af2896",
                            image = "https://i.scdn.co/image/ab6765630000ba8a77d267a5accb8911a92668e1"
                        ),
                        CategoryModel(
                            title = "Comedy",
                            bgColor = "#af2896",
                            image = "https://i.scdn.co/image/ab6765630000ba8a77d267a5accb8911a92668e1"
                        ),
                        CategoryModel(
                            title = "Comedy",
                            bgColor = "#af2896",
                            image = "https://i.scdn.co/image/ab6765630000ba8a77d267a5accb8911a92668e1"
                        ),
                    )
                )
            )
        )
    }
}