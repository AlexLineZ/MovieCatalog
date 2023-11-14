package com.example.moviecatalog.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.presentation.navigation.Destinations
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.router.LogoutRouter
import com.example.moviecatalog.presentation.screen.errorscreen.ErrorScreen
import com.example.moviecatalog.presentation.screen.favouritescreen.FavoriteViewModel
import com.example.moviecatalog.presentation.screen.favouritescreen.FavouriteScreen
import com.example.moviecatalog.presentation.screen.mainscreen.MainScreen
import com.example.moviecatalog.presentation.screen.mainscreen.MainViewModel
import com.example.moviecatalog.presentation.screen.moviescreen.MovieScreen
import com.example.moviecatalog.presentation.screen.moviescreen.MovieViewModel
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileScreen
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileViewModel
import com.example.moviecatalog.presentation.screen.selectauthscreen.SelectAuthScreen

const val MAIN_ROUTE = "main_root"

fun NavGraphBuilder.mainNavigationGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {
    var mainViewModel = MainViewModel()
    val favoriteViewModel = FavoriteViewModel()
    val movieViewModel = MovieViewModel(LogoutRouter(navController))

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
        composable(Destinations.ERROR_SCREEN) {
            ErrorScreen{ LogoutRouter(navController).toAuthAfterOut() }
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
    composable(Destinations.SELECT_AUTH_SCREEN) {
        mainViewModel = MainViewModel()
        SelectAuthScreen(router = AppRouter(navController))
    }
}