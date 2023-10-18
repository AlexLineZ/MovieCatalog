package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.data.model.TokenResponse
import com.example.moviecatalog.data.repository.AuthenticationRepository
import com.example.moviecatalog.domain.authorization.model.LoginData

class PostLoginDataUseCase {
    private val authenticationRepository = AuthenticationRepository()

    suspend fun invoke(loginData: LoginData) : Result<TokenResponse?> {
        val response = authenticationRepository.postLoginData(loginData)
        return if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            Result.success(null)
        }
    }
}