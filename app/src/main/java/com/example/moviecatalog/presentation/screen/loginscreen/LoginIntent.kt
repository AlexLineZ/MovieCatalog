package com.example.moviecatalog.presentation.screen.loginscreen

import com.example.moviecatalog.domain.state.LoginState

sealed class LoginIntent {
    data class Login(val loginState: LoginState) : LoginIntent()
    data class UpdateLogin(val login: String) : LoginIntent()
    data class UpdatePassword(val password: String) : LoginIntent()
    object UpdatePasswordVisibility : LoginIntent()

    object UpdateError : LoginIntent()
    data class UpdateErrorText(val errorText: String?) : LoginIntent()
}
