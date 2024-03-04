package com.him.sama.spotifycompose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.him.sama.spotifycompose.common.component.TabletPlayer
import com.him.sama.spotifycompose.common.ui.R
import com.him.sama.spotifycompose.common.ui.preview.TabletPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.nautral_50
import com.him.sama.spotifycompose.common.ui.theme.secondary_background
import com.him.sama.spotifycompose.common.ui.theme.tertiary_background
import com.him.sama.spotifycompose.common.ui.theme.unselected_color
import com.him.sama.spotifycompose.navigation.AppNavHost
import com.him.sama.spotifycompose.navigation.BottomBar

@Composable
fun TabletScreen(
    selectedDestination: MutableState<BottomBar>,
    navController: NavHostController,
) {
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
                modifier = Modifier.fillMaxWidth(0.20f),
                selectedDestination
            )
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
private fun LeftMenu(modifier: Modifier, selectedDestination: MutableState<BottomBar>) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(secondary_background)
            .padding(top = 42.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        MenuItem(BottomBar.HOME, selectedDestination.value == BottomBar.HOME)
        MenuItem(BottomBar.SEARCH, selectedDestination.value == BottomBar.SEARCH)
        MenuItem(BottomBar.YOUR_LIBRARY, selectedDestination.value == BottomBar.YOUR_LIBRARY)
        TabletPlayer(Modifier.weight(1f))
    }
}

@Composable
private fun MenuItem(bottomBar: BottomBar, isSelected: Boolean) {
    val icon = if (isSelected) bottomBar.selectedIcon else bottomBar.unselectedIcon
    val color = if (isSelected) MaterialTheme.colorScheme.onPrimary else unselected_color

    Row(
        modifier = Modifier
            .fillMaxWidth()
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
@Composable
private fun Preview() {
    AppTheme {
        TabletScreen(
            remember { mutableStateOf(BottomBar.HOME) },
            rememberNavController()
        )
    }
}