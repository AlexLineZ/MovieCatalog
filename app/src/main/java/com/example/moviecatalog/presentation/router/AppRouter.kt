package com.example.moviecatalog.presentation.router

import androidx.navigation.NavController
import com.example.moviecatalog.presentation.navigation.Destinations


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
        navController.navigate(Destinations.MAIN_SCREEN) {
            popUpTo(0)
        }
    }
}