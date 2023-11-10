package com.example.moviecatalog.presentation.router

import androidx.navigation.NavController
import com.example.moviecatalog.presentation.navigation.Destinations

class LogoutRouter(
    private val navController: NavController
) {
    fun toErrorAfterOut(){
        navController.navigate(Destinations.ERROR_SCREEN) {
            popUpTo(navController.graph.id){
                inclusive = true
            }
        }
    }
    fun toAuthAfterOut(){
        navController.navigate(Destinations.SELECT_AUTH_SCREEN) {
            popUpTo(navController.graph.id){
                inclusive = true
            }
        }
    }
}