package com.example.moviecatalog.data.repository

import com.example.moviecatalog.data.model.MovieDetailsResponse
import com.example.moviecatalog.data.model.MoviePageResponse
import com.example.moviecatalog.data.network.NetworkService
import retrofit2.Response

class MovieRepository {
    suspend fun getMovies(moviesPage: Int): Response<MoviePageResponse> {
        return NetworkService.movieApiService.getMovies(moviesPage)
    }

    suspend fun getMovieDetails(movieId: String): Response<MovieDetailsResponse> {
        return NetworkService.movieApiService.getMovieDetails(movieId)
    }
}