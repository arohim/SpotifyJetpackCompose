package com.him.sama.spotifycompose.feature.home.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.ui.theme.background_color
import com.him.sama.spotifycompose.common.ui.theme.tertiary_background

@Composable
internal fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(background_color),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .clip(CircleShape),
            model = "https://media.istockphoto.com/id/1129342275/th/%E0%B8%A3%E0%B8%B9%E0%B8%9B%E0%B8%96%E0%B9%88%E0%B8%B2%E0%B8%A2/%E0%B9%80%E0%B8%9E%E0%B8%A5%E0%B8%B4%E0%B8%94%E0%B9%80%E0%B8%9E%E0%B8%A5%E0%B8%B4%E0%B8%99%E0%B8%81%E0%B8%B1%E0%B8%9A%E0%B9%80%E0%B8%9E%E0%B8%A5%E0%B8%87%E0%B9%82%E0%B8%9B%E0%B8%A3%E0%B8%94%E0%B8%82%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%82%E0%B8%B2.jpg?s=1024x1024&w=is&k=20&c=TpYBPuy0gKY7MGzBnnIW2zhz3a76BgtGto4ZQIRkv4U=",
            contentScale = ContentScale.Crop,
            contentDescription = null
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