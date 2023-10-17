package com.example.moviecatalog.domain.state

data class LoginState (
    val login: String,
    val password: String,
    val isPasswordHide: Boolean
)