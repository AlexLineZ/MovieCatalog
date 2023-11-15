package com.example.moviecatalog.presentation.screen.loginscreen

sealed class LoginIntent {
    object Login : LoginIntent()
    object GoBack: LoginIntent()
    object GoToRegistration: LoginIntent()
    data class UpdateLogin(val login: String) : LoginIntent()
    data class UpdatePassword(val password: String) : LoginIntent()
    object UpdatePasswordVisibility : LoginIntent()

    object UpdateError: LoginIntent()
    data class UpdateErrorText(val errorText: String?) : LoginIntent()

    object UpdateLoading: LoginIntent()
}
