package com.example.moviecatalog.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.model.CurrentReview
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

const val MAIN_ROUTE = "main_root"

fun NavGraphBuilder.mainNavigationGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {
    val mainViewModel = MainViewModel()
    val favoriteViewModel = FavoriteViewModel()
    val movieViewModel = MovieViewModel()

    navigation(
        startDestination = Routes.HomeScreen.route,
        route = MAIN_ROUTE
    ) {
        composable(Routes.HomeScreen.route){navBackResult ->
            MainScreen(mainViewModel, BottomBarRouter(navController), navBackResult)
        }
        composable(Routes.Favourite.route) {
            FavouriteScreen(favoriteViewModel, BottomBarRouter(navController))
        }
        composable(Routes.Profile.route) {
            ProfileScreen(profileViewModel, BottomBarRouter(navController))
        }
        composable(Destinations.MOVIE_SCREEN){ backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            MovieScreen(
                navController = navController,
                viewModel = movieViewModel,
                movieId = movieId ?: Constants.EMPTY_STRING
            )
        }
    }
}