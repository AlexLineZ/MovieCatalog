package com.example.moviecatalog.domain.usecase

import android.util.Log
import com.example.moviecatalog.data.repository.ProfileRepository
import com.example.moviecatalog.domain.model.profile.Profile

class GetProfileUseCase {
    private val profileRepository = ProfileRepository()

    suspend fun invoke(): Result<Profile?> {
        return try {
            val response = profileRepository.getProfileData()
            return if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}