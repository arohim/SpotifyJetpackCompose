package com.him.sama.spotifycompose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.component.TabletPlayer
import com.him.sama.spotifycompose.common.ui.preview.AutomotivePreview
import com.him.sama.spotifycompose.common.ui.preview.TabletPreview
import com.him.sama.spotifycompose.common.ui.preview.TelevisionPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.secondary_background
import com.him.sama.spotifycompose.common.ui.theme.tertiary_background
import com.him.sama.spotifycompose.common.ui.theme.unselected_color
import com.him.sama.spotifycompose.navigation.BottomBar

@Composable
fun LeftMenu(
    modifier: Modifier,
    selectedDestination: MutableState<BottomBar>,
    onMenuClick: (BottomBar) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(secondary_background)
            .padding(top = 42.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        MenuItem(
            bottomBar = BottomBar.HOME,
            isSelected = selectedDestination.value == BottomBar.HOME
        ) {
            onMenuClick(BottomBar.HOME)
        }
        MenuItem(
            bottomBar = BottomBar.SEARCH,
            isSelected = selectedDestination.value == BottomBar.SEARCH
        ) {
            onMenuClick(BottomBar.SEARCH)
        }
        MenuItem(
            bottomBar = BottomBar.YOUR_LIBRARY,
            isSelected = selectedDestination.value == BottomBar.YOUR_LIBRARY
        ) {
            onMenuClick(BottomBar.YOUR_LIBRARY)
        }
        TabletPlayer(Modifier.weight(1f))
    }
}

@Composable
private fun MenuItem(bottomBar: BottomBar, isSelected: Boolean, onClick: () -> Unit) {
    val icon = if (isSelected) bottomBar.selectedIcon else bottomBar.unselectedIcon
    val color = if (isSelected) MaterialTheme.colorScheme.onPrimary else unselected_color

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .height(48.dp)
            .padding(vertical = 8.dp)
            .padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .border(4.dp, color = tertiary_background)
            )
        } else {
            Spacer(modifier = Modifier.width(4.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            modifier = Modifier.size(28.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = bottomBar.title),
            style = MaterialTheme.typography.bodyLarge,
            color = color
        )
    }
}

@TabletPreview
@TelevisionPreview
@AutomotivePreview
@Composable
fun PreviewLeftMenu() {
    AppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(it),
            ) {
                LeftMenu(
                    modifier = Modifier.fillMaxWidth(0.23f),
                    selectedDestination = remember { mutableStateOf(BottomBar.HOME) },
                    onMenuClick = {
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(it)
                )
            }
        }
    }
}
