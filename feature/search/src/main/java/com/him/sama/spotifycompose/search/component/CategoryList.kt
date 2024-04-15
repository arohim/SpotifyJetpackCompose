package com.him.sama.spotifycompose.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.extension.fromHex
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.search.CategoryModel

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun CategoryList(windowSize: WindowSize, categories: List<CategoryModel>) {
    val column = when (windowSize.width) {
        WindowType.Mobile -> {
            2
        }

        WindowType.Tablet -> {
            4
        }

        else -> {
            6
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = "Browse all",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            modifier = Modifier,
            maxItemsInEachRow = column,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            categories.forEach {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .background(
                            Color.fromHex(it.bgColor),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = it.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Card(
                        modifier = Modifier
                            .rotate(20f)
                            .offset(x = 20.dp, y = (-10).dp)
                            .align(Alignment.BottomEnd)
                            .size(70.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier,
                            model = it.image,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCategoryList() {
    AppTheme {
        CategoryList(
            windowSize = WindowSize(
                width = WindowType.Mobile,
                height = WindowType.Mobile
            ),
            categories = listOf(
                CategoryModel(
                    title = "Made For You",
                    bgColor = "#1e3264",
                    image = "https://t.scdn.co/images/ea364e99656e46a096ea1df50f581efe"
                ),
                CategoryModel(
                    title = "Made For You",
                    bgColor = "#1e3264",
                    image = "https://t.scdn.co/images/ea364e99656e46a096ea1df50f581efe"
                ),
                CategoryModel(
                    title = "Made For You",
                    bgColor = "#1e3264",
                    image = "https://t.scdn.co/images/ea364e99656e46a096ea1df50f581efe"
                ),
                CategoryModel(
                    title = "Made For You",
                    bgColor = "#1e3264",
                    image = "https://t.scdn.co/images/ea364e99656e46a096ea1df50f581efe"
                )
            )
        )
    }
}