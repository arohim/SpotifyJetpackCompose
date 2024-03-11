package com.him.sama.spotifycompose.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.ui.R
import com.him.sama.spotifycompose.common.ui.theme.background_color
import com.him.sama.spotifycompose.common.ui.theme.black_text_box_color

@Composable
fun BlackSearchBox() {
    TextField(
        modifier = Modifier
            .background(background_color, RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = black_text_box_color,
            focusedContainerColor = black_text_box_color
        ),
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = "What do you want to listen to?",
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xff787878)
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.encore_icon_search_24),
                contentDescription = null,
                tint = Color.White
            )
        },
        value = "",
        onValueChange = {},
    )
}