package com.example.moviecatalog.domain.model.authorization

data class RegistrationData(
    val userName: String,
    val name: String,
    val password: String,
    val email: String,
    val birthDate: String,
    val gender: Int
)
