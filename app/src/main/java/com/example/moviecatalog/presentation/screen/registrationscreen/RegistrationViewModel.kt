package com.example.moviecatalog.presentation.screen.registrationscreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.localstorage.LocalStorage
import com.example.moviecatalog.domain.authorization.model.LoginData
import com.example.moviecatalog.domain.authorization.model.RegistrationData
import com.example.moviecatalog.domain.state.RegistrationState
import com.example.moviecatalog.domain.usecase.DataValidateUseCase
import com.example.moviecatalog.domain.usecase.PostRegistrationDataUseCase
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
        null
    )

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<RegistrationState> get() = _state

    private val postRegistrationDataUseCase = PostRegistrationDataUseCase()
    private val dataValidateUseCase = DataValidateUseCase()

    fun processIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.Continue -> {

            }
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
                performRegistration(state.value)
            }

            RegistrationIntent.UpdateError -> {
                _state.value = state.value.copy(
                    isError = !_state.value.isError && !isContinueButtonAvailable()
                )
            }
            is RegistrationIntent.UpdateErrorText -> {
                _state.value = state.value.copy(isErrorText = intent.errorText)
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
                !state.value.isError
    }

    private fun performRegistration(registrationState: RegistrationState) {
        val registrationData = RegistrationData(
            userName = registrationState.login,
            name = registrationState.name,
            password = registrationState.password,
            email = registrationState.email,
            birthDate = registrationState.birthday,
            gender = registrationState.gender
        )

        viewModelScope.launch {
            try {
                val result = postRegistrationDataUseCase.invoke(registrationData)
                if (result.isSuccess) {
                    val tokenResponse = result.getOrNull()
                    if (tokenResponse != null) {
                        Log.d("OMG", tokenResponse.token)
                    }
                    LocalStorage(context).saveToken(tokenResponse!!)
                } else {
                    Log.d("Mem", "hahaha")
                }
            } catch (e: Exception) {
                Log.d("ERROR", e.message.toString())
            }
        }
    }
}