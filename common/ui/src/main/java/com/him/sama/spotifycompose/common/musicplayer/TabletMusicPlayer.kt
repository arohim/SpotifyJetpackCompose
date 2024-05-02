package com.him.sama.spotifycompose.common.musicplayer

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.ui.R
import com.him.sama.spotifycompose.common.ui.theme.nautral_50
import com.him.sama.spotifycompose.common.ui.theme.unselected_color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabletPlayer(
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        AsyncImage(
            modifier = Modifier
                .height(220.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp)),
            model = viewState.selectedItem?.image,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier,
            ) {
                Text(
                    text = viewState.selectedItem?.title ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = viewState.selectedItem?.description ?: "",
                    style = MaterialTheme.typography.labelLarge,
                    color = unselected_color
                )
            }
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Slider(
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    inactiveTrackColor = nautral_50,
                    activeTrackColor = Color.White
                ),
                valueRange = 0f..100f,
                value = 50f,
                onValueChange = {}
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "2:47",
                style = MaterialTheme.typography.labelSmall,
                color = unselected_color
            )
            Text(
                text = "-4:07",
                style = MaterialTheme.typography.labelSmall,
                color = unselected_color
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.baseline_play_circle_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.baseline_skip_next_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_speaker_group_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Devices available",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}