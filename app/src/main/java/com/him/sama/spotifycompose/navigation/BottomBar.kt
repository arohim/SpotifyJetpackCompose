package com.him.sama.spotifycompose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.him.sama.spotifycompose.common.ui.R


enum class BottomBar(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {
    HOME(
        title = R.string.bottom_bar_home_title,
        route = HOME_ROUTE,
        selectedIcon = R.drawable.encore_icon_home_active_24,
        unselectedIcon = R.drawable.encore_icon_home_24
    ),
    SEARCH(
        title = R.string.bottom_bar_search_title,
        route = PASSIVE_INCOME_ROUTE,
        selectedIcon = R.drawable.encore_icon_search_active_24,
        unselectedIcon = R.drawable.encore_icon_search_24
    ),
    YOUR_LIBRARY(
        title = R.string.bottom_bar_your_library_title,
        route = SETTING_ROUTE,
        selectedIcon = R.drawable.encore_icon_collection_active_24,
        unselectedIcon = R.drawable.encore_icon_collection_24
    )
}