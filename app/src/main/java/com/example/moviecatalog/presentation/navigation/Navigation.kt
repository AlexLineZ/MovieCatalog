package com.example.moviecatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.presentation.router.LoginRouter
import com.example.moviecatalog.presentation.ui.loginscreen.LoginScreen
import com.example.moviecatalog.presentation.ui.loginscreen.LoginViewModel
import com.example.moviecatalog.presentation.ui.mainscreen.MainScreen
import com.example.moviecatalog.presentation.ui.registrationscreen.registationfirstscreen.RegistrationFirstScreen
import com.example.moviecatalog.presentation.ui.registrationscreen.registrationsecondscreen.RegistrationSecondScreen
import com.example.moviecatalog.presentation.ui.selectauthscreen.SelectAuthScreen
import com.example.moviecatalog.presentation.ui.splashscreen.SplashScreen

object Destinations {
    const val SPLASH_SCREEN = "splash"
    const val SELECT_AUTH_SCREEN = "both"
    const val REGISTRATION_FIRST_SCREEN = "registrationFirst"
    const val REGISTRATION_SECOND_SCREEN = "registrationSecond"
    const val LOGIN_SCREEN = "login"
    const val MAIN_SCREEN = "main"
}

@Composable
fun Navigation(viewModel: LoginViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.SPLASH_SCREEN
    ) {
        composable (Destinations.SPLASH_SCREEN){
            SplashScreen(LoginRouter(navController))
        }
        composable(Destinations.SELECT_AUTH_SCREEN) {
            SelectAuthScreen(LoginRouter(navController))
        }
        composable(Destinations.LOGIN_SCREEN) {
            LoginScreen(LoginRouter(navController), viewModel)
        }
        composable(Destinations.REGISTRATION_FIRST_SCREEN) {
            RegistrationFirstScreen(LoginRouter(navController))
        }
        composable(Destinations.REGISTRATION_SECOND_SCREEN) {
            RegistrationSecondScreen(LoginRouter(navController))
        }
        composable(Destinations.MAIN_SCREEN) {
            MainScreen()
        }
    }
}