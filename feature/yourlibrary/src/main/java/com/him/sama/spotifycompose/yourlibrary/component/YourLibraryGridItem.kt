package com.him.sama.spotifycompose.yourlibrary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.state.rememberWindowSize
import com.him.sama.spotifycompose.common.ui.theme.unselected_color

@Composable
fun YourLibraryGridItem(
    modifier: Modifier,
    image: String,
    title: String
) {
    val windowSize = rememberWindowSize()
    val textStyle = when (windowSize.width) {
        WindowType.Automotive, WindowType.Mobile -> MaterialTheme.typography.bodyLarge
        WindowType.Television, WindowType.Tablet -> MaterialTheme.typography.bodyLarge
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clip(shape = RoundedCornerShape(4.dp)),
            model = image,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = textStyle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            color = unselected_color
        )
    }
}