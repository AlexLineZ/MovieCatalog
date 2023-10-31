package com.example.moviecatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.screen.favouritescreen.FavouriteScreen
import com.example.moviecatalog.presentation.screen.mainscreen.MainScreen
import com.example.moviecatalog.presentation.screen.mainscreen.MainViewModel
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileScreen
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileViewModel

const val BOTTOM_BAR_ROUTE = "bottomBar"

@Composable
fun BottomBarNavigation(bottomBarController: NavHostController) {
    val mainViewModel = MainViewModel()
    val profileViewModel = ProfileViewModel(LocalContext.current)

    NavHost(
        navController = bottomBarController,
        startDestination = Routes.HomeScreen.route,
        route = BOTTOM_BAR_ROUTE
    ) {
        composable(Routes.HomeScreen.route){
            MainScreen(mainViewModel)
        }
        composable(Routes.Favourite.route) {
            FavouriteScreen()
        }
        composable(Routes.Profile.route) {
            ProfileScreen(profileViewModel)
        }
    }
}