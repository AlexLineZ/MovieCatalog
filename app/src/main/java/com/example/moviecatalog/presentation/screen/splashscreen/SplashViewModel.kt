package com.example.moviecatalog.presentation.screen.splashscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import com.example.moviecatalog.domain.usecase.GetTokenUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    private val getProfileUseCase = GetProfileUseCase()

    fun checkTokenToValid(
        context: Context,
        isSuccess: () -> Unit,
        isFailure: () -> Unit
    ){
        val getTokenUseCase = GetTokenUseCase(context)

        viewModelScope.launch {
            val token = getTokenUseCase.invoke()
            NetworkService.setAuthToken(token.token)
        }

        viewModelScope.launch{
            val result = getProfileUseCase.invoke()
            if (result.isSuccess){
                launch {
                    delay(800L)
                    isSuccess()
                }
            } else {
                launch {
                    delay(800L)
                    isFailure()
                }
            }
        }
    }
}