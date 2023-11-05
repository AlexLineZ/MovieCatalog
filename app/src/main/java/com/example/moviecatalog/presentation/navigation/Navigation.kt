package com.example.moviecatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.screen.loginscreen.LoginScreen
import com.example.moviecatalog.presentation.screen.loginscreen.LoginViewModel
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileViewModel
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
    const val MOVIE_SCREEN = "movie/{movieId}"
}

const val ROOT_ROUTE = "root"

@Composable
fun Navigation(
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel
) {
    val navController = rememberNavController()
    val profileViewModel = ProfileViewModel(LocalContext.current)
    NavHost(
        navController = navController,
        startDestination = Destinations.SPLASH_SCREEN,
        route = ROOT_ROUTE
    ) {
        authNavigationGraph(
            navController = navController,
            loginViewModel = loginViewModel,
            registrationViewModel = registrationViewModel
        )
        mainNavigationGraph(
            navController = navController,
            profileViewModel = profileViewModel
        )
    }
}