package com.example.moviecatalog.presentation.screen.registrationscreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.localstorage.LocalStorage
import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.model.authorization.Registration
import com.example.moviecatalog.domain.state.RegistrationState
import com.example.moviecatalog.domain.usecase.DataValidateUseCase
import com.example.moviecatalog.domain.usecase.PostRegistrationUseCase
import com.example.moviecatalog.domain.validator.ConfirmPasswordValidator
import com.example.moviecatalog.domain.validator.EmailValidator
import com.example.moviecatalog.domain.validator.PasswordValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel (private val context: Context) : ViewModel() {
    private val emptyState = RegistrationState(
        Constants.EMPTY_STRING,
        Constants.ZERO,
        Constants.EMPTY_STRING,
        Constants.EMPTY_STRING,
        Constants.EMPTY_STRING,
        Constants.EMPTY_STRING,
        Constants.FALSE,
        Constants.FALSE,
        Constants.EMPTY_STRING,
        Constants.EMPTY_STRING,
        Constants.FALSE,
        Constants.FALSE,
        Constants.FALSE,
        null, null, null,
        Constants.FALSE
    )

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<RegistrationState> get() = _state

    private val postRegistrationUseCase = PostRegistrationUseCase()
    private val dataValidateUseCase = DataValidateUseCase()

    fun processIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.UpdateBirthday -> {
                _state.value = state.value.copy(birthday = intent.birthday)
                _state.value = state.value.copy(date = intent.date)
            }
            is RegistrationIntent.UpdateDatePickerVisibility -> {
                _state.value = state.value.copy(
                    isDatePickerOpened = !_state.value.isDatePickerOpened
                )
            }
            is RegistrationIntent.UpdateEmail -> {
                _state.value = state.value.copy(email = intent.email)
            }
            is RegistrationIntent.UpdateGender -> {
                _state.value = state.value.copy(gender = intent.gender)
            }
            is RegistrationIntent.UpdateLogin -> {
                _state.value = state.value.copy(login = intent.login)
            }
            is RegistrationIntent.UpdateName -> {
                _state.value = state.value.copy(name = intent.name)
            }
            is RegistrationIntent.UpdateConfirmPassword -> {
                _state.value = state.value.copy(confirmPassword = intent.confirmPassword)
            }
            is RegistrationIntent.UpdateConfirmPasswordVisibility -> {
                _state.value = state.value.copy(
                    isConfirmPasswordHide = !_state.value.isConfirmPasswordHide
                )
            }
            is RegistrationIntent.UpdatePassword -> {
                _state.value = state.value.copy(password = intent.password)
            }
            is RegistrationIntent.UpdatePasswordVisibility -> {
                _state.value = state.value.copy(
                    isPasswordHide = !_state.value.isPasswordHide
                )
            }
            is RegistrationIntent.Registration -> {
                performRegistration(state.value, intent.afterRegistration)
            }
            is RegistrationIntent.UpdateErrorText -> {
                var result = dataValidateUseCase.invoke(intent.validator, intent.data, intent.secondData)
                when (intent.validator) {
                    is EmailValidator -> _state.value = state.value.copy (
                        isErrorEmailText = result?.let { context.getString(it) }
                    )
                    is PasswordValidator -> _state.value = state.value.copy (
                        isErrorPasswordText = result?.let { context.getString(it) }
                    )
                    is ConfirmPasswordValidator -> _state.value = state.value.copy (
                        isErrorConfirmPasswordText = result?.let { context.getString(it) }
                    )
                }
            }

            RegistrationIntent.UpdateLoading -> {
                _state.value = state.value.copy(
                    isLoading = !_state.value.isLoading
                )
            }
        }
    }

    fun isDatePickerOpen() : Boolean {
        return state.value.isDatePickerOpened
    }

    fun isContinueButtonAvailable() : Boolean {
        return  state.value.name.isNotEmpty() &&
                state.value.login.isNotEmpty() &&
                state.value.email.isNotEmpty() &&
                state.value.date.isNotEmpty() &&
                state.value.isErrorEmailText == null
    }

    fun isRegisterButtonAvailable() : Boolean {
        return  state.value.password.isNotEmpty() &&
                state.value.confirmPassword.isNotEmpty() &&
                state.value.isErrorPasswordText == null &&
                state.value.isErrorConfirmPasswordText == null
    }

    private fun performRegistration(
        registrationState: RegistrationState,
        afterRegistration: () -> Unit
    ) {
        val registration = Registration(
            userName = registrationState.login,
            name = registrationState.name,
            password = registrationState.password,
            email = registrationState.email,
            birthDate = registrationState.birthday,
            gender = registrationState.gender
        )

        processIntent(RegistrationIntent.UpdateLoading)
        viewModelScope.launch {
            try {
                val result = postRegistrationUseCase.invoke(registration)
                if (result.isSuccess) {
                    val tokenResponse = result.getOrNull()
                    LocalStorage(context).saveToken(tokenResponse!!)
                    NetworkService.setAuthToken(tokenResponse.token)
                    afterRegistration()
                } else {
                    Toast.makeText(
                        context,
                        "Ошибка регистрации",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Ошибка соединения с сервером",
                    Toast.LENGTH_SHORT
                ).show()
            } finally {
                processIntent(RegistrationIntent.UpdateLoading)
            }
        }
    }
}