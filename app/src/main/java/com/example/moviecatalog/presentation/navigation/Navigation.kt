package com.example.moviecatalog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.presentation.navigation.graph.authNavigationGraph
import com.example.moviecatalog.presentation.navigation.graph.mainNavigationGraph
import com.example.moviecatalog.presentation.router.LogoutRouter
import com.example.moviecatalog.presentation.screen.loginscreen.LoginViewModel
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileViewModel
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationViewModel
import com.example.moviecatalog.presentation.screen.splashscreen.SplashViewModel


const val ROOT_ROUTE = "root"

@Composable
fun Navigation(
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel
) {
    val navController = rememberNavController()

    val splashViewModel = SplashViewModel()
    val profileViewModel = ProfileViewModel(
        context = LocalContext.current,
        router = LogoutRouter(navController)
    )
    NavHost(
        navController = navController,
        startDestination = Destinations.SPLASH_SCREEN,
        route = ROOT_ROUTE
    ) {
        authNavigationGraph(
            navController = navController,
            splashViewModel = splashViewModel,
            loginViewModel = loginViewModel,
            registrationViewModel = registrationViewModel
        )
        mainNavigationGraph(
            navController = navController,
            profileViewModel = profileViewModel
        )
    }
}