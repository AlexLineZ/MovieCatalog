package com.example.moviecatalog.presentation.screen.registrationscreen

import com.example.moviecatalog.domain.authorization.model.RegistrationData
import com.example.moviecatalog.domain.state.RegistrationState
import com.example.moviecatalog.presentation.screen.loginscreen.LoginIntent

sealed class RegistrationIntent {
    data class Continue(val loginState: RegistrationState) : RegistrationIntent()
    data class UpdateName(val name: String) : RegistrationIntent()
    data class UpdateGender(val gender: Int) : RegistrationIntent()
    data class UpdateLogin(val login: String) : RegistrationIntent()
    data class UpdateEmail(val email: String) : RegistrationIntent()
    data class UpdateBirthday(val birthday: String, val date: String) : RegistrationIntent()
    object UpdateDatePickerVisibility : RegistrationIntent()

    data class UpdatePassword(val password: String) : RegistrationIntent()
    data class UpdateConfirmPassword(val confirmPassword: String) : RegistrationIntent()
    object UpdatePasswordVisibility : RegistrationIntent()
    object UpdateConfirmPasswordVisibility : RegistrationIntent()

    data class Registration(val registrationState: RegistrationState) : RegistrationIntent()

    object UpdateError : RegistrationIntent()
    data class UpdateErrorText(val errorText: String?) : RegistrationIntent()
}