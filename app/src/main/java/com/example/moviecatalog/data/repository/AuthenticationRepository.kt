package com.example.moviecatalog.data.repository

import com.example.moviecatalog.data.model.TokenResponse
import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.authorization.model.LoginData
import com.example.moviecatalog.domain.authorization.model.RegistrationData
import retrofit2.Response

class AuthenticationRepository {

    suspend fun postLoginData(authorizationData: LoginData): Response<TokenResponse> {
        return NetworkService.authenticationApiService.postLoginData(authorizationData)
    }

    suspend fun postRegistrationData(registrationData: RegistrationData): Response<TokenResponse> {
        return NetworkService.authenticationApiService.postRegistrationData(registrationData)
    }

    suspend fun postLogoutData(token: TokenResponse) {
        NetworkService.authenticationApiService.postLogoutData(token.token)
    }
}