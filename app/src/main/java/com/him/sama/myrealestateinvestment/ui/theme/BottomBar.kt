package com.him.sama.myrealestateinvestment.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


const val HOME_ROUTE = "home_route"
const val TRANSACTION_ROUTE = "transaction_route"
const val SETTING_ROUTE = "profile_route"

enum class BottomBar(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {
    HOME(
        title = R.string.bottom_bar_home_title,
        route = HOME_ROUTE,
        selectedIcon = android.R.drawable.ic_menu_gallery,
        unselectedIcon = R.drawable.ic_home_unselected_24dp
    ),
    TRANSACTION(
        title = R.string.bottom_bar_portfolio_title,
        route = TRANSACTION_ROUTE,
        selectedIcon = R.drawable.ic_portfolio_selected_24dp,
        unselectedIcon = R.drawable.ic_portfoliol_unselected_24dp
    ),
    SETTING(
        title = R.string.bottom_bar_buy_sell_title,
        route = SETTING_ROUTE,
        selectedIcon = android.R.drawable.
        unselectedIcon = 0
    )
}