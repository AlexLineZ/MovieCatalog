package com.example.moviecatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.presentation.router.LoginRouter
import com.example.moviecatalog.presentation.ui.screen.loginscreen.LoginScreen
import com.example.moviecatalog.presentation.ui.screen.mainscreen.MainScreen
import com.example.moviecatalog.presentation.ui.screen.registationfirstscreen.RegistrationFirstScreen
import com.example.moviecatalog.presentation.ui.screen.registrationsecondscreen.RegistrationSecondScreen
import com.example.moviecatalog.presentation.ui.screen.selectauthscreen.SelectAuthScreen
import com.example.moviecatalog.presentation.ui.screen.splashscreen.SplashScreen

object Destinations {
    const val SPLASH_SCREEN = "splash"
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
        startDestination = Destinations.SPLASH_SCREEN
    ) {

        val router = LoginRouter(
            toLogin = { navController.navigate(Destinations.LOGIN_SCREEN) {
                popUpTo(Destinations.SELECT_AUTH_SCREEN)
            } },
            toRegistration = { navController.navigate(Destinations.REGISTRATION_FIRST_SCREEN) {
                popUpTo(Destinations.SELECT_AUTH_SCREEN)
            } },
            toAuth = { navController.navigate(Destinations.SELECT_AUTH_SCREEN){
                popUpTo(Destinations.SPLASH_SCREEN) { inclusive = true }
            } },
            toPasswordRegistration = { navController.navigate(Destinations.REGISTRATION_SECOND_SCREEN) },
            toMain = { navController.navigate(Destinations.MAIN_SCREEN){
                popUpTo(0)
            }
            }
        )

        composable (Destinations.SPLASH_SCREEN){
            SplashScreen(router)
        }
        composable(Destinations.SELECT_AUTH_SCREEN) {
            SelectAuthScreen(router)
        }
        composable(Destinations.LOGIN_SCREEN) {
            LoginScreen(router)
        }
        composable(Destinations.REGISTRATION_FIRST_SCREEN) {
            RegistrationFirstScreen(router)
        }
        composable(Destinations.REGISTRATION_SECOND_SCREEN) {
            RegistrationSecondScreen(router)
        }
        composable(Destinations.MAIN_SCREEN) {
            MainScreen()
        }
    }
}