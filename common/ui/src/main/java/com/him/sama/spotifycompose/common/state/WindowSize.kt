package com.him.sama.spotifycompose.common.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

data class WindowSize(
    val width: WindowType,
    val height: WindowType
)

enum class WindowType {
    Mobile,
    Tablet,
    Automotive,
    Television
}

@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableIntStateOf(configuration.screenWidthDp)
    }
    val screenHeight by remember(key1 = configuration) {
        mutableIntStateOf(configuration.screenHeightDp)
    }

    return WindowSize(
        width = getScreenWidth(screenWidth),
        height = getScreenHeight(screenHeight)
    )
}

fun getScreenWidth(width: Int): WindowType = when {
    width < 600 -> WindowType.Mobile
    width < 840 -> WindowType.Tablet
    else -> WindowType.Automotive
}

fun getScreenHeight(height: Int): WindowType = when {
    height < 480 -> WindowType.Mobile
    height < 900 -> WindowType.Tablet
    else -> WindowType.Automotive
}