package com.example.moviecatalog.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.moviecatalog.presentation.navigation.MainNavigation
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.screen.mainscreen.HalfScreenPager

@Composable
fun BottomBarScreen(router: BottomBarRouter) {
    Scaffold(
        bottomBar = {
            BottomBar(
                router = router
            )
        }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            router.SetNavigation()
        }
    }
}