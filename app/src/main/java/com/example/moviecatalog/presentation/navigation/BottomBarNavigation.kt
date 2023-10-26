package com.example.moviecatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.screen.mainscreen.FavouriteScreen
import com.example.moviecatalog.presentation.screen.mainscreen.MainScreen
import com.example.moviecatalog.presentation.screen.mainscreen.ProfileScreen

const val BOTTOM_BAR_ROUTE = "bottomBar"

@Composable
fun BottomBarNavigation(bottomBarController: NavHostController) {
    NavHost(
        navController = bottomBarController,
        startDestination = Routes.HomeScreen.route,
        route = BOTTOM_BAR_ROUTE
    ) {
        composable(Routes.HomeScreen.route){
            MainScreen()
        }
        composable(Routes.Favourite.route) {
            FavouriteScreen()
        }
        composable(Routes.Profile.route) {
            ProfileScreen()
        }
    }
}