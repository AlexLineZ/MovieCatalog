package com.example.moviecatalog.presentation.navigation.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.BottomBarColor

@Composable
fun BottomBar(
    router: BottomBarRouter
) {
    val screens = listOf(
        Routes.HomeScreen, Routes.Favourite, Routes.Profile
    )
    NavigationBar(
        containerColor = BottomBarColor
    ) {
        router.SetNavigationBackStack()

        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon (
                        imageVector = ImageVector.vectorResource(screen.icon!!),
                        contentDescription = null
                    )
                },

                selected = router.isSelected(screen.route),

                onClick = {
                    router.navigate(screen)
                },
                colors = NavigationBarItemDefaults
                    .colors(
                        selectedIconColor = AccentColor,
                        indicatorColor = BottomBarColor
                    )
            )
        }
    }
}