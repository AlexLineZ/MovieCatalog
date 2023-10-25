package com.example.moviecatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.screen.mainscreen.BottomBarScreen
import com.example.moviecatalog.presentation.screen.loginscreen.LoginScreen
import com.example.moviecatalog.presentation.screen.loginscreen.LoginViewModel
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationViewModel
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationFirstScreen
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationSecondScreen
import com.example.moviecatalog.presentation.screen.selectauthscreen.SelectAuthScreen
import com.example.moviecatalog.presentation.screen.splashscreen.SplashScreen

object Destinations {
    const val SPLASH_SCREEN = "splash"
    const val SELECT_AUTH_SCREEN = "both"
    const val REGISTRATION_FIRST_SCREEN = "registrationFirst"
    const val REGISTRATION_SECOND_SCREEN = "registrationSecond"
    const val LOGIN_SCREEN = "login"
    const val MAIN_SCREEN = "main"
}

const val ROOT_ROUTE = "root"

@Composable
fun Navigation(
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.SPLASH_SCREEN,
        route = ROOT_ROUTE
    ) {
        composable (Destinations.SPLASH_SCREEN){
            SplashScreen(AppRouter(navController))
        }
        composable(Destinations.SELECT_AUTH_SCREEN) {
            SelectAuthScreen(AppRouter(navController))
        }
        composable(Destinations.LOGIN_SCREEN) {
            LoginScreen(AppRouter(navController), loginViewModel)
        }
        composable(Destinations.REGISTRATION_FIRST_SCREEN) {
            RegistrationFirstScreen(AppRouter(navController), registrationViewModel)
        }
        composable(Destinations.REGISTRATION_SECOND_SCREEN) {
            RegistrationSecondScreen(AppRouter(navController), registrationViewModel)
        }
        composable(Destinations.MAIN_SCREEN){
            BottomBarScreen()
        }
    }
}