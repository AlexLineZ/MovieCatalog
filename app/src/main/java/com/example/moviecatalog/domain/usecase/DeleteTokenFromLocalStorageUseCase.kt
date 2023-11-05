package com.example.moviecatalog.domain.usecase

import android.content.Context
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.localstorage.LocalStorage
import com.example.moviecatalog.data.network.NetworkService

class DeleteTokenFromLocalStorageUseCase (private val context: Context) {
    private val localStorage = LocalStorage(context)

    fun invoke() {
        NetworkService.setAuthToken(Constants.EMPTY_STRING)
        return localStorage.removeToken()
    }
}