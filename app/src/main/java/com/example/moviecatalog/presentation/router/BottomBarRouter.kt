package com.example.moviecatalog.presentation.router

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviecatalog.presentation.navigation.MainNavigation
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import kotlinx.coroutines.flow.MutableStateFlow

class BottomBarRouter (private val navController: NavHostController) {
    private val currentScreen =  MutableStateFlow<String?>(null)

    fun navigate(screen: Routes) {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun SetNavigationBackStack() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        currentScreen.value = currentRoute
    }

    @Composable
    fun SetNavigation() {
        MainNavigation(navController)
    }

    fun isSelected(screen: String) : Boolean{
        return currentScreen.value == screen
    }
}
