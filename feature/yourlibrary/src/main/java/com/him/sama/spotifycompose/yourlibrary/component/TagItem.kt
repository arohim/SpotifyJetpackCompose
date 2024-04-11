package com.him.sama.spotifycompose.yourlibrary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.ui.theme.on_background_background

@Composable
internal fun TagItem(text: String) {
    Text(
        modifier = Modifier
            .background(on_background_background, CircleShape)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onPrimary
    )
}