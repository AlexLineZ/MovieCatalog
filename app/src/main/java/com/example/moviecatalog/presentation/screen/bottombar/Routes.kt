package com.example.moviecatalog.presentation.screen.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object HomeScreen : Routes(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Favourite : Routes(
        route = "favourite_screen",
        title = "Favorite",
        icon = Icons.Outlined.Favorite
    )

    object Notification : Routes(
        route = "notification_screen",
        title = "Notification",
        icon = Icons.Outlined.Notifications
    )

}
