package com.example.moviecatalog.domain.usecase

import android.util.Log
import com.example.moviecatalog.data.model.TokenResponse
import com.example.moviecatalog.data.repository.AuthenticationRepository
import com.example.moviecatalog.domain.model.authorization.Registration

class PostRegistrationUseCase {
    private val authenticationRepository = AuthenticationRepository()

    suspend fun invoke(registration: Registration) : Result<TokenResponse?> {
        val response = authenticationRepository.postRegistration(registration)
        return if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    }

}