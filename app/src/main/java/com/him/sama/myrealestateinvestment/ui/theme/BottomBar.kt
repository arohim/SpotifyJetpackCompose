package com.him.sama.myrealestateinvestment.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.him.sama.myrealestateinvestment.R
import com.him.sama.myrealestateinvestment.common.ui.R.*
import com.him.sama.myrealestateinvestment.ui.navigation.HOME_ROUTE
import com.him.sama.myrealestateinvestment.ui.navigation.PASSIVE_INCOME_ROUTE
import com.him.sama.myrealestateinvestment.ui.navigation.SETTING_ROUTE


enum class BottomBar(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {
    HOME(
        title = string.bottom_bar_home_title,
        route = HOME_ROUTE,
        selectedIcon = R.drawable.ic_round_home_24,
        unselectedIcon = R.drawable.ic_round_home_24
    ),
    PASSIVE_INCOME(
        title = string.bottom_bar_passive_income_title,
        route = PASSIVE_INCOME_ROUTE,
        selectedIcon = R.drawable.ic_round_account_balance_wallet_24,
        unselectedIcon = R.drawable.ic_round_account_balance_wallet_24
    ),
    SETTING(
        title = string.bottom_bar_profile_title,
        route = SETTING_ROUTE,
        selectedIcon = R.drawable.ic_round_account_circle_24,
        unselectedIcon = R.drawable.ic_round_account_circle_24
    )
}