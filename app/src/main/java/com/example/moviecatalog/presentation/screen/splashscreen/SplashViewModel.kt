package com.example.moviecatalog.presentation.screen.splashscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import com.example.moviecatalog.domain.usecase.GetTokenUseCase
import com.example.moviecatalog.presentation.router.AppRouter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val router: AppRouter
): ViewModel() {
    private val getProfileUseCase = GetProfileUseCase()

    fun checkTokenToValid(
        context: Context
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
                    router.toMain()
                }
            } else {
                launch {
                    router.toAuth()
                }
            }
        }
    }
}