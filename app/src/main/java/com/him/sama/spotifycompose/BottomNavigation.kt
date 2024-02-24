package com.him.sama.spotifycompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.modifiers.bounceClick
import com.him.sama.spotifycompose.common.ui.theme.SpotifyJetpackComposeTheme
import com.him.sama.spotifycompose.navigation.BottomBar

@Composable
fun BottomNavigation(
    selectedDestination: BottomBar,
    onClick: (BottomBar) -> Unit
) {
    Surface(
        color = Color.Transparent,
        contentColor = Color.Transparent,
        tonalElevation = NavigationBarDefaults.Elevation,
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                .height(80.0.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.Center,
            content = {
                BottomBar.entries.forEach { bottomBar ->
                    val color = if (selectedDestination == bottomBar) {
                        Color.White
                    } else {
                        Color.White.copy(alpha = 0.6f)
                    }
                    val icon = if (selectedDestination == bottomBar) {
                        bottomBar.selectedIcon
                    } else {
                        bottomBar.unselectedIcon
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(115.dp)
                            .clip(shape = CircleShape)
                            .bounceClick(
                                onClick = {
                                    onClick(bottomBar)
                                },
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            tint = color
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(id = bottomBar.title),
                            style = MaterialTheme.typography.labelMedium,
                            color = color
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewBottomNavigation() {
    SpotifyJetpackComposeTheme {
        val selectedDestination = remember { mutableStateOf(BottomBar.SEARCH) }
        BottomNavigation(selectedDestination = selectedDestination.value, onClick = {

        })
    }
}