package com.example.moviecatalog.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalog.presentation.navigation.Navigation
import com.example.moviecatalog.presentation.ui.loginscreen.LoginViewModel
import com.example.moviecatalog.presentation.ui.loginscreen.LoginViewModelFactory
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
                    val viewModel = ViewModelProvider(
                        this, LoginViewModelFactory()
                    )[LoginViewModel::class.java]

                    Navigation(viewModel)
                }
            }
        }
    }
}