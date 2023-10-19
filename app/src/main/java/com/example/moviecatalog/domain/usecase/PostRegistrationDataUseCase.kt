package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.data.model.TokenResponse
import com.example.moviecatalog.data.repository.AuthenticationRepository
import com.example.moviecatalog.domain.authorization.model.RegistrationData

class PostRegistrationDataUseCase {
    private val authenticationRepository = AuthenticationRepository()

    suspend fun invoke(registrationData: RegistrationData) : Result<TokenResponse?> {
        val response = authenticationRepository.postRegistrationData(registrationData)
        return if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            Result.success(null)
        }
    }

}