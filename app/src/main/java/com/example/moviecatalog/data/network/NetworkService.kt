package com.example.moviecatalog.data.network

import com.example.moviecatalog.data.network.api.AuthenticationApiService
import com.example.moviecatalog.data.network.api.FavoriteApiService
import com.example.moviecatalog.data.network.api.MovieApiService
import com.example.moviecatalog.data.network.api.ProfileApiService
import com.example.moviecatalog.data.network.api.ReviewApiService
import com.example.moviecatalog.data.network.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {
    private const val BASE_URL = "https://react-midterm.kreosoft.space/"

    private val authInterceptor = AuthInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun setAuthToken(token: String) {
        authInterceptor.setAuthToken(token)
    }

    val authenticationApiService: AuthenticationApiService =
        retrofit.create(AuthenticationApiService::class.java)

    val movieApiService: MovieApiService =
        retrofit.create(MovieApiService::class.java)

    val profileApiService: ProfileApiService =
        retrofit.create(ProfileApiService::class.java)

    val favoriteApiService: FavoriteApiService =
        retrofit.create(FavoriteApiService::class.java)

    val reviewApiService: ReviewApiService =
        retrofit.create(ReviewApiService::class.java)
}