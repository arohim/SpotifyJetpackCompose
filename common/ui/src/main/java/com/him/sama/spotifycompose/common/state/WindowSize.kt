package com.him.sama.spotifycompose.common.state

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

data class WindowSize(
    val width: WindowType,
    val height: WindowType
)

/**
 * Mobile width (411dp)
 * Television width (960dp)
 * Tablet width (1280dp)
 * Automotive width (1440dp)
 */
enum class WindowType {
    Mobile,
    Television,
    Tablet,
    Automotive
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
    width < 1280 -> WindowType.Television
    width < 1440 -> WindowType.Tablet
    else -> WindowType.Automotive
}

fun getScreenHeight(height: Int): WindowType = when {
    height < 600 -> WindowType.Mobile
    height < 1280 -> WindowType.Television
    height < 1440 -> WindowType.Tablet
    else -> WindowType.Automotive
}