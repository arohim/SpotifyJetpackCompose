package com.him.sama.spotifycompose.yourlibrary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun TagItem(text: String) {
    Text(
        modifier = Modifier
            .background(Color(0xff2a2a2a), CircleShape)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onPrimary
    )
}