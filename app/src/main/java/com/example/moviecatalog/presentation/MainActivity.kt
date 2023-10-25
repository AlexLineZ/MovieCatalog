package com.example.moviecatalog.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalog.presentation.factory.LoginViewModelFactory
import com.example.moviecatalog.presentation.factory.RegistrationViewModelFactory
import com.example.moviecatalog.presentation.navigation.Navigation
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.screen.loginscreen.LoginViewModel
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationViewModel
import com.example.moviecatalog.presentation.ui.theme.MovieCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieCatalogTheme (darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val loginViewModel = ViewModelProvider(
                        this, LoginViewModelFactory(application)
                    )[LoginViewModel::class.java]

                    val regViewModel = ViewModelProvider(
                        this, RegistrationViewModelFactory(application)
                    )[RegistrationViewModel::class.java]

                    Navigation(
                        loginViewModel = loginViewModel,
                        registrationViewModel = regViewModel
                    )
                }
            }
        }
    }
}