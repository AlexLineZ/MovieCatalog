package com.example.moviecatalog.presentation.screen.loginscreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.localstorage.LocalStorage
import com.example.moviecatalog.domain.authorization.model.LoginData
import com.example.moviecatalog.domain.state.LoginState
import com.example.moviecatalog.domain.usecase.PostLoginDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel (private val context: Context) : ViewModel() {
    private val emptyState = LoginState(
        Constants.EMPTY_STRING,
        Constants.EMPTY_STRING,
        Constants.FALSE)

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<LoginState> get() = _state

    private val postLoginDataUseCase = PostLoginDataUseCase()

    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> {
                performLogin(intent.loginState.login, intent.loginState.password)
            }
            is LoginIntent.UpdateLogin -> {
                _state.value = state.value.copy(login = intent.login)
            }
            is LoginIntent.UpdatePassword -> {
                _state.value = state.value.copy(password = intent.password)
            }
            is LoginIntent.UpdatePasswordVisibility -> {
                _state.value = state.value.copy(isPasswordHide = !_state.value.isPasswordHide)
            }
        }
    }

    private fun performLogin(username: String, password: String) {
        val loginData = LoginData(username, password)
        viewModelScope.launch {
            try {
                val result = postLoginDataUseCase.invoke(loginData)
                if (result.isSuccess) {
                    val tokenResponse = result.getOrNull()
                    if (tokenResponse != null) {
                        Log.d("OMG", tokenResponse.token)
                    }
                    LocalStorage(context).saveToken(tokenResponse!!)
                } else {

                }
            } catch (e: Exception) {

            }
        }
    }
}