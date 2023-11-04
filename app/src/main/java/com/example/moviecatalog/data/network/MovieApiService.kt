package com.example.moviecatalog.data.network

import com.example.moviecatalog.data.model.MovieDetailsResponse
import com.example.moviecatalog.data.model.MoviePageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("api/movies/{page}")
    suspend fun getMovies(@Path("page") moviesPage: Int): Response<MoviePageResponse>

    @GET("api/movies/details/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: String): Response<MovieDetailsResponse>
}