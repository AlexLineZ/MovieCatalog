package com.example.moviecatalog.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviecatalog.presentation.navigation.Destinations
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.screen.loginscreen.LoginScreen
import com.example.moviecatalog.presentation.screen.loginscreen.LoginViewModel
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationFirstScreen
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationSecondScreen
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationViewModel
import com.example.moviecatalog.presentation.screen.selectauthscreen.SelectAuthScreen
import com.example.moviecatalog.presentation.screen.splashscreen.SplashScreen
import com.example.moviecatalog.presentation.screen.splashscreen.SplashViewModel

const val AUTH_ROUTE = "auth_root"

fun NavGraphBuilder.authNavigationGraph(
    navController: NavHostController,
    splashViewModel: SplashViewModel,
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel
) {
    navigation(
        startDestination = Destinations.SPLASH_SCREEN,
        route = AUTH_ROUTE
    ) {
        composable (Destinations.SPLASH_SCREEN){
            SplashScreen(splashViewModel, AppRouter(navController))
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
    }
    composable (Destinations.SPLASH_SCREEN){
        SplashScreen(splashViewModel, AppRouter(navController))
    }
}