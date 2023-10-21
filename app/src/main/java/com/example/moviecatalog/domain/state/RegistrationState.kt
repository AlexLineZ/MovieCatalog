package com.example.moviecatalog.domain.state

data class RegistrationState (
    val name: String,
    val gender: Int,
    val login: String,
    val email: String,
    val birthday: String,
    val date: String,

    val isDatePickerOpened: Boolean,
    val isSecondScreenAvailable: Boolean,

    val password: String,
    val confirmPassword: String,

    val isPasswordHide: Boolean,
    val isConfirmPasswordHide: Boolean,

    val isError: Boolean,
    val isErrorText: String?
)
