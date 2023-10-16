package com.example.moviecatalog.presentation.ui.loginscreen

import android.util.Log
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.common.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState(Constants.EMPTY_STRING, Constants.EMPTY_STRING))
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
        }
    }
}