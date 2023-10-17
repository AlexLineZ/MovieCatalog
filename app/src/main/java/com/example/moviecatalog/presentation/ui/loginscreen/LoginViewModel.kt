package com.example.moviecatalog.presentation.ui.loginscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.domain.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LoginViewModel : ViewModel() {
    private val emptyState = LoginState("", "", false)

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<LoginState> get() = _state

    fun updateLogin(login: String) {
        _state.value = state.value.copy(login = login)
    }

    fun updatePassword(password: String) {
        _state.value = state.value.copy(password = password)
    }

    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> {
                Log.d(state.value.login, state.value.password)
            }
            is LoginIntent.UpdateLogin -> {
                _state.value = state.value.copy(login = intent.login)
            }
            is LoginIntent.UpdatePassword -> {
                _state.value = state.value.copy(password = intent.password)
            }
            LoginIntent.UpdatePasswordVisibility -> {
                _state.value = state.value.copy(isPasswordHide = !_state.value.isPasswordHide)
            }
        }
    }
}