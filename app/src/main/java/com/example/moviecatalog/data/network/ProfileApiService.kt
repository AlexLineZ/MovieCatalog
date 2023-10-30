package com.example.moviecatalog.data.network

import com.example.moviecatalog.domain.model.profile.Profile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileApiService {
    @GET("api/account/profile")
    suspend fun getProfileData(): Response<Profile>

    @PUT("api/account/profile")
    suspend fun putProfileData(@Body profile: Profile)
    : Response<Unit>
}