package com.example.moviecatalog.presentation.screen.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.router.AppRouter
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(router: AppRouter) {
    LaunchedEffect(key1 = true){
        delay(3000L)
        router.toAuth()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.launch_screen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}