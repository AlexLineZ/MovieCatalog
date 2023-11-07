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
                Log.d("ProfileSuccessful", response.code().toString())
                Result.success(response.body()!!)
            } else {
                Log.d("ProfileElse", response.code().toString())
                Result.failure(exception = Throwable())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}