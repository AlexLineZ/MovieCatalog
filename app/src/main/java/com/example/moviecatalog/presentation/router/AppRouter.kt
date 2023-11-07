package com.example.moviecatalog.presentation.router

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.moviecatalog.presentation.navigation.AUTH_ROUTE
import com.example.moviecatalog.presentation.navigation.Destinations
import com.example.moviecatalog.presentation.navigation.ROOT_ROUTE
import com.example.moviecatalog.presentation.navigation.bottombar.Routes


class AppRouter(
    private val navController: NavController
) {
    fun toLogin() {
        navController.navigate(Destinations.LOGIN_SCREEN) {
            popUpTo(Destinations.SELECT_AUTH_SCREEN)
        }
    }

    fun toRegistration() {
        navController.navigate(Destinations.REGISTRATION_FIRST_SCREEN) {
            popUpTo(Destinations.SELECT_AUTH_SCREEN)
        }
    }

    fun toAuth() {
        navController.navigate(Destinations.SELECT_AUTH_SCREEN) {
            popUpTo(Destinations.SPLASH_SCREEN) { inclusive = true }
        }
    }

    fun toPasswordRegistration() {
        navController.navigate(Destinations.REGISTRATION_SECOND_SCREEN)
    }

    fun toMain() {
        navController.navigate(Routes.HomeScreen.route) {
            popUpTo(Destinations.SPLASH_SCREEN) {
                inclusive = true
            }
        }
    }
}
