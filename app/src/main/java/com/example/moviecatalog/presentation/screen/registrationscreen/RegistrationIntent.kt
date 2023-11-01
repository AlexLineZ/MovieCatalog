package com.example.moviecatalog.presentation.screen.registrationscreen

import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.domain.state.RegistrationState
import com.example.moviecatalog.domain.validator.Validator
import com.example.moviecatalog.presentation.screen.loginscreen.LoginIntent

sealed class RegistrationIntent {
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

    data class UpdateErrorText(
        val validator: Validator,
        val data: String,
        val secondData: String = Constants.EMPTY_STRING
    ): RegistrationIntent()

    object UpdateLoading: RegistrationIntent()
}