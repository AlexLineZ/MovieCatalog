package com.example.moviecatalog.presentation.screen.loginscreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.localstorage.LocalStorage
import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.model.authorization.LoginData
import com.example.moviecatalog.domain.state.LoginState
import com.example.moviecatalog.domain.usecase.PostLoginDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel (private val context: Context) : ViewModel() { //AndroidViewModel
    private val emptyState = LoginState(
        "string",
        "string",
        Constants.FALSE,
        Constants.FALSE,
        null,
        Constants.FALSE
    )

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<LoginState> get() = _state

    private val postLoginDataUseCase = PostLoginDataUseCase()

    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> {
                performLogin(intent.loginState.login, intent.loginState.password, intent.afterLogin)
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
            LoginIntent.UpdateError -> {
                _state.value = state.value.copy(isError = !_state.value.isError)
            }
            is LoginIntent.UpdateErrorText -> {
                _state.value = state.value.copy(isErrorText = intent.errorText)
            }
            LoginIntent.UpdateLoading -> {
                _state.value = state.value.copy(isLoading = !_state.value.isLoading)
            }

            else -> {}
        }
    }

    fun isLoginButtonAvailable() : Boolean {
        return  state.value.password.isNotEmpty() &&
                state.value.login.isNotEmpty()
    }

    private fun performLogin(username: String, password: String, routeAfterLogin: () -> Unit) {
        val loginData = LoginData(username, password)
        processIntent(LoginIntent.UpdateLoading)
        viewModelScope.launch {
            try {
                val result = postLoginDataUseCase.invoke(loginData)
                if (result.isSuccess) {
                    val tokenResponse = result.getOrNull()
                    if (tokenResponse != null) {
                        NetworkService.setAuthToken(tokenResponse.token)
                    }
                    LocalStorage(context).saveToken(tokenResponse!!)
                    routeAfterLogin()
                } else {
                    processIntent(LoginIntent.UpdateErrorText(context.getString(R.string.auth_error)))
                }
            } catch (e: Exception) {
                Log.d("ERROR", e.message.toString())
            } finally {
                processIntent(LoginIntent.UpdateLoading)
            }
        }
    }
}