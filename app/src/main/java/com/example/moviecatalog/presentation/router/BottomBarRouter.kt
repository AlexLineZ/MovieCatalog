package com.example.moviecatalog.presentation.router

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviecatalog.presentation.navigation.Destinations
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import kotlinx.coroutines.flow.MutableStateFlow

class BottomBarRouter (private val navController: NavHostController) {
    fun navigate(screen: Routes) {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    fun toMovie(movieId: String) {
        val routeWithId = Destinations.MOVIE_SCREEN.replace("{movieId}", movieId)
        navController.navigate(routeWithId)
    }
}
