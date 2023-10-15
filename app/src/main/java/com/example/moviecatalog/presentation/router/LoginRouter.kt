package com.example.moviecatalog.presentation.router

data class LoginRouter(
    val toLogin: () -> Unit,
    val toRegistration: () -> Unit
)
