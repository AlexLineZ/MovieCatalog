package com.example.moviecatalog.domain.usecase

import android.content.Context
import com.example.moviecatalog.data.localstorage.LocalStorage
import com.example.moviecatalog.data.model.TokenResponse

class GetTokenFromLocalStorageUseCase(private val context: Context) {
    private val localStorage = LocalStorage(context)

    fun invoke(): TokenResponse {
        return localStorage.getToken()
    }
}