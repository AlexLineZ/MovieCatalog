package com.example.moviecatalog.data.network

import com.example.moviecatalog.data.model.Token
import com.example.moviecatalog.domain.authorization.model.LoginData
import com.example.moviecatalog.domain.authorization.model.RegistrationData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("api/account/register")
    suspend fun postRegistrationData(@Body registrationData: RegistrationData): Response<Token>

    @POST("api/account/login")
    suspend fun postLoginData(@Body loginData: LoginData): Response<Token>

    @POST("api/account/logout")
    suspend fun logout()
}