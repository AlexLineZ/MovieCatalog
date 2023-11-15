package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.data.repository.ProfileRepository
import com.example.moviecatalog.domain.model.profile.Profile

class PutProfileDataUseCase {
    private val profileRepository = ProfileRepository()

    suspend fun invoke(profile: Profile): Result<Unit?> {
        return try {
            val response = profileRepository.putProfileData(profile)
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