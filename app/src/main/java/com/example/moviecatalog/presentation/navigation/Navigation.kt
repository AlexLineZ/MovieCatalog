package com.example.moviecatalog.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.router.LoginRouter
import com.example.moviecatalog.presentation.screen.loginscreen.LoginScreen
import com.example.moviecatalog.presentation.screen.loginscreen.LoginViewModel
import com.example.moviecatalog.presentation.screen.mainscreen.FavouriteScreen
import com.example.moviecatalog.presentation.screen.mainscreen.MainScreen
import com.example.moviecatalog.presentation.screen.mainscreen.ProfileScreen
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

@Composable
fun Navigation(
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel
) {
    val navController = rememberNavController()
    val navController1 = rememberNavController()

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
            LoginScreen(LoginRouter(navController), loginViewModel)
        }
        composable(Destinations.REGISTRATION_FIRST_SCREEN) {
            RegistrationFirstScreen(LoginRouter(navController), registrationViewModel)
        }
        composable(Destinations.REGISTRATION_SECOND_SCREEN) {
            RegistrationSecondScreen(LoginRouter(navController), registrationViewModel)
        }
        composable(Destinations.MAIN_SCREEN) {

        }
    }
}