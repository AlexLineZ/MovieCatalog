package com.example.moviecatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.presentation.ui.screen.LoginScreen
import com.example.moviecatalog.presentation.ui.screen.RegistrationFirstScreen
import com.example.moviecatalog.presentation.ui.screen.SelectAuthScreen

object Destinations {
    const val SELECT_AUTH_SCREEN = "both"
    const val REGISTRATION_FIRST_SCREEN = "registrationFirst"
    const val REGISTRATION_SECOND_SCREEN = "registrationSecond"
    const val LOGIN_SCREEN = "login"
    const val MAIN_SCREEN = "main"
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.SELECT_AUTH_SCREEN
    ) {
        composable(Destinations.LOGIN_SCREEN) {
            LoginScreen()
        }
        composable(Destinations.REGISTRATION_FIRST_SCREEN) {
            RegistrationFirstScreen()
        }
        composable(Destinations.SELECT_AUTH_SCREEN) {
            SelectAuthScreen()
        }
    }
}