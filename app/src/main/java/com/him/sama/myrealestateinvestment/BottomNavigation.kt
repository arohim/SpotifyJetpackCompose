package com.him.sama.myrealestateinvestment

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.him.sama.myrealestateinvestment.navigation.BottomBar

@Composable
fun BottomNavigation(selectedDestination: MutableState<BottomBar>) {
    NavigationBar(
        modifier = Modifier.height(56.dp)
    ) {
        BottomBar.entries.forEach { bottomBar ->
            NavigationBarItem(
                selected = selectedDestination.value == bottomBar,
                onClick = {
                    selectedDestination.value = bottomBar
                },
                icon = {
                    Icon(
                        painterResource(id = bottomBar.selectedIcon),
                        contentDescription = null
                    )
                }
            )
        }
    }
}