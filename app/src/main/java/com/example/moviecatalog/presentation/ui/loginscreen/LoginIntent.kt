package com.example.moviecatalog.presentation.ui.loginscreen

sealed class LoginIntent {
    data class Login(val loginState: LoginState) : LoginIntent()
}
