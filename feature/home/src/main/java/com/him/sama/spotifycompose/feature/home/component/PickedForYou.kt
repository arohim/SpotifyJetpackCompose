package com.him.sama.spotifycompose.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.ui.R
import com.him.sama.spotifycompose.common.ui.theme.on_background_background
import com.him.sama.spotifycompose.common.ui.theme.unselected_color
import com.him.sama.spotifycompose.feature.home.HomeDetailItem
import com.him.sama.spotifycompose.feature.home.HomeItem
import com.him.sama.spotifycompose.feature.home.HomeLayoutType
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PickedForYou(data: HomeItem) {
    val item = data.items.firstOrNull()
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = data.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(
                    color = on_background_background,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(shape = RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(170.dp)
                    .width(160.dp)
                    .background(color = Color.LightGray),
                model = item?.image ?: "",
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    text = "Play list",
                    style = MaterialTheme.typography.bodySmall,
                    color = unselected_color
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item?.title ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item?.description ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = unselected_color,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(id = R.drawable.baseline_play_circle_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PickedForYou(
        data = HomeItem(
            layoutType = HomeLayoutType.PLAY_WIDGET,
            image = "",
            title = "Pick for you",
            items = persistentListOf(
                HomeDetailItem(
                    image = "",
                    title = "Jazz in the rain",
                    description = "description".repeat(10),
                    categoryName = "",
                    categoryHierarchy = ""
                )
            )
        )
    )
}