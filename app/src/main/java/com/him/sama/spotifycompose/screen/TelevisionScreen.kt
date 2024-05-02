package com.him.sama.spotifycompose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.musicplayer.TvMusicPlayer
import com.him.sama.spotifycompose.common.ui.preview.TelevisionPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.background_color
import com.him.sama.spotifycompose.navigation.AppNavHost
import com.him.sama.spotifycompose.navigation.BottomBar

@Composable
fun TelevisionScreen(
    selectedDestination: MutableState<BottomBar>,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background_color)
                .padding(it),
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            TVTopMenu(
                selectedDestination = selectedDestination
            ) {
                selectedDestination.value = it
            }
            AppNavHost(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(it),
                navController = navController,
                startDestination = selectedDestination.value.route
            )
        }
    }
}

@Composable
fun TVTopMenu(
    selectedDestination: MutableState<BottomBar>,
    onMenuClick: (BottomBar) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
    ) {
        TvMusicPlayer(modifier = Modifier.align(Alignment.CenterStart))
        MainMenu(
            modifier = Modifier.align(Alignment.Center),
            selectedDestination = selectedDestination,
            onMenuClick = onMenuClick
        )
        UserSetting(
            modifier = Modifier.align(Alignment.CenterEnd),
        )
    }
}

@Composable
fun UserSetting(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = com.him.sama.spotifycompose.common.ui.R.drawable.baseline_settings_24),
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(16.dp))
        AsyncImage(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .clip(CircleShape),
            model = "https://media.istockphoto.com/id/1129342275/th/%E0%B8%A3%E0%B8%B9%E0%B8%9B%E0%B8%96%E0%B9%88%E0%B8%B2%E0%B8%A2/%E0%B9%80%E0%B8%9E%E0%B8%A5%E0%B8%B4%E0%B8%94%E0%B9%80%E0%B8%9E%E0%B8%A5%E0%B8%B4%E0%B8%99%E0%B8%81%E0%B8%B1%E0%B8%9A%E0%B9%80%E0%B8%9E%E0%B8%A5%E0%B8%87%E0%B9%82%E0%B8%9B%E0%B8%A3%E0%B8%94%E0%B8%82%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%82%E0%B8%B2.jpg?s=1024x1024&w=is&k=20&c=TpYBPuy0gKY7MGzBnnIW2zhz3a76BgtGto4ZQIRkv4U=",
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Composable
private fun MainMenu(
    modifier: Modifier,
    selectedDestination: MutableState<BottomBar>,
    onMenuClick: (BottomBar) -> Unit
) {
    Row(
        modifier = modifier,
    ) {
        MenuItem(
            bottomBar = BottomBar.HOME,
            isSelected = selectedDestination.value == BottomBar.HOME,
            onClick = {
                onMenuClick(BottomBar.HOME)
            }
        )
        MenuItem(
            bottomBar = BottomBar.SEARCH,
            isSelected = selectedDestination.value == BottomBar.SEARCH,
            onClick = {
                onMenuClick(BottomBar.SEARCH)
            }
        )
        MenuItem(
            bottomBar = BottomBar.YOUR_LIBRARY,
            isSelected = selectedDestination.value == BottomBar.YOUR_LIBRARY,
            onClick = {
                onMenuClick(BottomBar.YOUR_LIBRARY)
            }
        )
    }
}

@Composable
private fun MenuItem(bottomBar: BottomBar, isSelected: Boolean, onClick: () -> Unit) {
    val icon = if (isSelected) bottomBar.selectedIcon else bottomBar.unselectedIcon
    val color = Color.White

    val modifier = if (isSelected)
        Modifier
            .clickable(onClick = onClick)
            .border(
                2.dp,
                color = Color.White,
                shape = CircleShape,
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)
    else
        Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(14.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = bottomBar.title),
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

@TelevisionPreview
@Composable
private fun Preview() {
    AppTheme {
        TelevisionScreen(
            remember { mutableStateOf(BottomBar.HOME) },
            rememberNavController()
        )
    }
}