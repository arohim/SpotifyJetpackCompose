package com.him.sama.myrealestateinvestment.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.him.sama.myrealestateinvestment.common.ui.R.drawable
import com.him.sama.myrealestateinvestment.common.ui.R.string


enum class BottomBar(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {
    HOME(
        title = string.bottom_bar_home_title,
        route = HOME_ROUTE,
        selectedIcon = drawable.encore_icon_home_active_24,
        unselectedIcon = drawable.encore_icon_home_24
    ),
    SEARCH(
        title = string.bottom_bar_search_title,
        route = PASSIVE_INCOME_ROUTE,
        selectedIcon = drawable.encore_icon_search_active_24,
        unselectedIcon = drawable.encore_icon_search_24
    ),
    YOUR_LIBRARY(
        title = string.bottom_bar_your_library_title,
        route = SETTING_ROUTE,
        selectedIcon = drawable.encore_icon_collection_active_24,
        unselectedIcon = drawable.encore_icon_collection_24
    )
}