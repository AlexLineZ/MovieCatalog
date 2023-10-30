package com.example.moviecatalog.presentation.navigation.bottombar

import com.example.moviecatalog.R

sealed class Routes(
    val route: String,
    val icon: Int? = null
) {
    object HomeScreen : Routes(
        route = "home_screen",
        icon = R.drawable.main_unfocused
    )

    object Favourite : Routes(
        route = "favourite_screen",
        icon = R.drawable.favourite_unfocused
    )

    object Profile : Routes(
        route = "profile_screen",
        icon = R.drawable.profile_unfocused
    )
}
