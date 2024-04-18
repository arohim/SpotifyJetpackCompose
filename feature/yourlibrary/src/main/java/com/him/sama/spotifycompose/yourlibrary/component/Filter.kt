package com.him.sama.spotifycompose.yourlibrary.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.state.WindowSize
import com.him.sama.spotifycompose.common.state.WindowType
import com.him.sama.spotifycompose.common.ui.R

@Composable
internal fun Filter(
    windowSize: WindowSize,
    showGrid: Boolean = false,
    onToggleGrid: (() -> Unit)? = null
) {
    val iconSize = when (windowSize.width) {
        WindowType.Automotive, WindowType.Mobile -> 16.dp
        WindowType.Television, WindowType.Tablet -> 24.dp
    }
    val fontSize = when (windowSize.width) {
        WindowType.Automotive, WindowType.Mobile -> MaterialTheme.typography.labelMedium
        WindowType.Television, WindowType.Tablet -> MaterialTheme.typography.bodyLarge
    }

    val sortingIcon = if (showGrid) {
        R.drawable.baseline_format_list_bulleted_24
    } else {
        R.drawable.baseline_grid_view_24
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            painter = painterResource(id = R.drawable.baseline_import_export_24),
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Recent",
            style = fontSize,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        if (onToggleGrid != null) {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable(onClick = onToggleGrid),
                painter = painterResource(id = sortingIcon),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}