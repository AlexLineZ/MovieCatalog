package com.example.moviecatalog.data.network

import com.example.moviecatalog.data.model.TokenResponse
import com.example.moviecatalog.domain.model.authorization.LoginData
import com.example.moviecatalog.domain.model.authorization.RegistrationData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApiService {
    @POST("api/account/register")
    suspend fun postRegistrationData(@Body registrationData: RegistrationData): Response<TokenResponse>

    @POST("api/account/login")
    suspend fun postLoginData(@Body loginData: LoginData): Response<TokenResponse>

    @POST("api/account/logout")
    suspend fun postLogoutData(): Response<Unit>
}