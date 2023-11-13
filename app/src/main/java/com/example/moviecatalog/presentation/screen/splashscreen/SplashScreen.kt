package com.example.moviecatalog.presentation.screen.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.router.AppRouter

@Composable
fun SplashScreen(splashViewModel: SplashViewModel) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        splashViewModel.checkTokenToValid(
            context = context
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}