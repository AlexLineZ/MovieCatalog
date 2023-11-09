package com.example.moviecatalog.data.network

import com.example.moviecatalog.data.model.TokenResponse
import com.example.moviecatalog.domain.model.authorization.Login
import com.example.moviecatalog.domain.model.authorization.Registration
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApiService {
    @POST("api/account/register")
    suspend fun postRegistration(@Body registration: Registration): Response<TokenResponse>

    @POST("api/account/login")
    suspend fun postLogin(@Body login: Login): Response<TokenResponse>

    @POST("api/account/logout")
    suspend fun postLogout(): Response<Unit>
}