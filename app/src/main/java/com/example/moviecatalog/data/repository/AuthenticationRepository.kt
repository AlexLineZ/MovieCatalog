package com.example.moviecatalog.data.repository

import com.example.moviecatalog.data.model.TokenResponse
import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.model.authorization.Login
import com.example.moviecatalog.domain.model.authorization.Registration
import retrofit2.Response

class AuthenticationRepository {

    suspend fun postLogin(authorizationData: Login): Response<TokenResponse> {
        return NetworkService.authenticationApiService.postLogin(authorizationData)
    }

    suspend fun postRegistration(registration: Registration): Response<TokenResponse> {
        return NetworkService.authenticationApiService.postRegistration(registration)
    }

    suspend fun postLogout(): Response<Unit> {
        return NetworkService.authenticationApiService.postLogout()
    }
}