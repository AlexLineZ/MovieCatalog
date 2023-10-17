package com.example.moviecatalog.presentation.ui.registrationscreen

import com.example.moviecatalog.domain.state.RegistrationState

sealed class RegistrationIntent {
    data class Continue(val loginState: RegistrationState) : RegistrationIntent()
    data class UpdateName(val name: String) : RegistrationIntent()
    data class UpdateGender(val gender: Int) : RegistrationIntent()
    data class UpdateLogin(val login: String) : RegistrationIntent()
    data class UpdateEmail(val email: String) : RegistrationIntent()
    data class UpdateBirthday(val birthday: String) : RegistrationIntent()
    object UpdateDatePickerVisibility : RegistrationIntent()

    data class UpdatePassword(val password: String) : RegistrationIntent()
    data class UpdateConfirmPassword(val confirmPassword: String) : RegistrationIntent()
    object UpdatePasswordVisibility : RegistrationIntent()
    object UpdateConfirmPasswordVisibility : RegistrationIntent()

}