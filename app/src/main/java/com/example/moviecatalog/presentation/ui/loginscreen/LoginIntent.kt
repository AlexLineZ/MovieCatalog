package com.example.moviecatalog.presentation.ui.loginscreen

import com.example.moviecatalog.domain.state.LoginState

sealed class LoginIntent {
    data class Login(val loginState: LoginState) : LoginIntent()
}
