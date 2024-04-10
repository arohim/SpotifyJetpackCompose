package com.him.sama.spotifycompose.feature.home.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.feature.home.HomeDetailItem

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
internal fun RecommendationSection(windowSize: WindowSize, items: List<HomeDetailItem>) {
    val maxItemsInEachRow = when (windowSize.width) {
        WindowType.Mobile -> {
            2
        }

        WindowType.Television -> {
            4
        }

        else -> {
            3
        }
    }
    val widthFraction = 1.0f / maxItemsInEachRow

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        maxItemsInEachRow = maxItemsInEachRow,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach {
            RecommendationItem(
                widthFraction = widthFraction,
                data = it
            )
        }
    }
}

@Composable
private fun RecommendationItem(widthFraction: Float, data: HomeDetailItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth(widthFraction - 0.011f)
            .height(56.dp)
            .background(
                color = Color.LightGray.copy(alpha = 0.4f),
                shape = RoundedCornerShape(4.dp)
            )
            .clip(shape = RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .width(56.dp)
                .background(color = Color.LightGray),
            model = data.image,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = data.title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}
