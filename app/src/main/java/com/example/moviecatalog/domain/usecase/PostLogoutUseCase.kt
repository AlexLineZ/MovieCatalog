package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.data.repository.AuthenticationRepository

class PostLogoutUseCase {
    private val authenticationRepository = AuthenticationRepository()

    suspend fun invoke() : Result<Unit?> {
        val response = authenticationRepository.postLogoutData()

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