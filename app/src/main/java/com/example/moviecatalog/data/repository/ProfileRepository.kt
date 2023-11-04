package com.example.moviecatalog.data.repository

import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.model.profile.Profile
import retrofit2.Response

class ProfileRepository {
    suspend fun getProfileData(): Response<Profile> {
        return NetworkService.profileApiService.getProfileData()
    }

    suspend fun putProfileData(profile: Profile) : Response<Unit> {
        return NetworkService.profileApiService.putProfileData(profile)
    }
}