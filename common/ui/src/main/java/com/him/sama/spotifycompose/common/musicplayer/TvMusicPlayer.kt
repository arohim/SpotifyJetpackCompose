package com.him.sama.spotifycompose.common.musicplayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.ui.theme.unselected_color

@Composable
fun TvMusicPlayer(
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    Row(
        modifier = modifier
            .height(48.dp)
            .width(200.dp)
            .background(
                color = Color(0xff232323),
                shape = RoundedCornerShape(4.dp)
            )
            .clip(shape = RoundedCornerShape(4.dp))
            .padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .width(48.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clip(RoundedCornerShape(4.dp)),
            model = viewState.selectedItem?.image,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier,
        ) {
            Text(
                text = viewState.selectedItem?.title ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
            Text(
                text = viewState.selectedItem?.description ?: "",
                style = MaterialTheme.typography.labelSmall,
                color = unselected_color
            )
        }
    }
}