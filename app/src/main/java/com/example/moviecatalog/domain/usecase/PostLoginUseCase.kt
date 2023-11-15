package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.data.model.TokenResponse
import com.example.moviecatalog.data.repository.AuthenticationRepository
import com.example.moviecatalog.domain.model.authorization.Login

class PostLoginUseCase {
    private val authenticationRepository = AuthenticationRepository()

    suspend fun invoke(login: Login) : Result<TokenResponse?> {
        val response = authenticationRepository.postLogin(login)

        return try {
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}