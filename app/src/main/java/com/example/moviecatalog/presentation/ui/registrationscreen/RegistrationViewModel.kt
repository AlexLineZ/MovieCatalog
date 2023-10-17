package com.example.moviecatalog.presentation.ui.registrationscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.domain.state.RegistrationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegistrationViewModel : ViewModel() {
    private val emptyState = RegistrationState(
        "",
        0,
        "",
        "",
        "",
        false,
        false,
        "",
        ""
    )

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<RegistrationState> get() = _state

    fun processIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.Continue -> {

            }
            is RegistrationIntent.UpdateBirthday -> {
                _state.value = state.value.copy(birthday = intent.birthday)
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
        }
    }
}