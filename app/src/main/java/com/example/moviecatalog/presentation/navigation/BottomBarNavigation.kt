package com.example.moviecatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.screen.favouritescreen.FavoriteViewModel
import com.example.moviecatalog.presentation.screen.favouritescreen.FavouriteScreen
import com.example.moviecatalog.presentation.screen.mainscreen.MainScreen
import com.example.moviecatalog.presentation.screen.mainscreen.MainViewModel
import com.example.moviecatalog.presentation.screen.moviescreen.MovieScreen
import com.example.moviecatalog.presentation.screen.moviescreen.MovieViewModel
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileScreen
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileViewModel

const val BOTTOM_BAR_ROUTE = "bottomBar"

@Composable
fun BottomBarNavigation(bottomBarController: NavHostController) {
    val mainViewModel = MainViewModel()
    val profileViewModel = ProfileViewModel(LocalContext.current)
    val favoriteViewModel = FavoriteViewModel()
    val movieViewModel = MovieViewModel()

    NavHost(
        navController = bottomBarController,
        startDestination = Routes.HomeScreen.route,
        route = BOTTOM_BAR_ROUTE
    ) {
        composable(Routes.HomeScreen.route){
            MainScreen(mainViewModel, BottomBarRouter(bottomBarController))
        }
        composable(Routes.Favourite.route) {
            FavouriteScreen(favoriteViewModel)
        }
        composable(Routes.Profile.route) {
            ProfileScreen(profileViewModel)
        }
        composable(Destinations.MOVIE_SCREEN){
            MovieScreen({ bottomBarController.popBackStack() }, movieViewModel)
        }
    }
}