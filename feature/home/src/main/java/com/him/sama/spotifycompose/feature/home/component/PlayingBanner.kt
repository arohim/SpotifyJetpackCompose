package com.him.sama.spotifycompose.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.ui.theme.nautral_50

@Composable
internal fun PlayingBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Jazz in the Rain",
                style = MaterialTheme.typography.displaySmall,
                color = Color.White
            )
            Text(
                text = "Podcast * Music",
                style = MaterialTheme.typography.bodyMedium,
                color = nautral_50
            )
        }
        AsyncImage(
            modifier = Modifier
                .size(150.dp)
                .background(color = Color.LightGray, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp)),
            model = "https://i.scdn.co/image/ab67706f00000002f46ec304be9222df641117a2",
            contentDescription = null,
        )
    }
}