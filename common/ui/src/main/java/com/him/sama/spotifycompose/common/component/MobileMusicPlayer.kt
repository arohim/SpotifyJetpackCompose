package com.him.sama.spotifycompose.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.on_background_background
import com.him.sama.spotifycompose.common.ui.theme.secondary_background

@Composable
fun MobileMusicPlayer() {
    Box(
        modifier = Modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    on_background_background,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.wrapContentHeight()
            ) {
                Text(
                    text = "Lindo",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Lindo",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(fraction = 0.8f)
                .padding(horizontal = 24.dp)
                .background(Color.White)
                .height(0.5.dp)
        )
    }
}

@Preview
@Composable
fun PreviewPlayerComponent() {
    AppTheme {
        MobileMusicPlayer()
    }
}