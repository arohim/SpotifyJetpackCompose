package com.him.sama.spotifycompose.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.ui.theme.tertiary_background

@Composable
internal fun Header() {
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
private fun Tags() {
    TagItem("All", true)
    Spacer(modifier = Modifier.width(8.dp))
    TagItem("Music", false)
    Spacer(modifier = Modifier.width(8.dp))
    TagItem("Podcasts", false)
}

@Composable
private fun TagItem(title: String, isSelected: Boolean) {
    Text(
        modifier = Modifier
            .background(
                color = if (isSelected)
                    tertiary_background
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