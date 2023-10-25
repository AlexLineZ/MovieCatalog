package com.example.moviecatalog.presentation.screen.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.presentation.navigation.MainNavigation
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.router.BottomBarRouter

@Composable
fun BottomBarScreen() {
    val bottomBarController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                router = BottomBarRouter(bottomBarController),
                navController = bottomBarController
            )
        }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            MainNavigation(bottomBarController)
        }
    }
}