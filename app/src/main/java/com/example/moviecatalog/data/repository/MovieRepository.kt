package com.example.moviecatalog.data.repository

import com.example.moviecatalog.domain.model.movie.MovieDetails
import com.example.moviecatalog.domain.model.movie.MoviesPagedList
import com.example.moviecatalog.data.network.NetworkService
import retrofit2.Response

class MovieRepository {
    suspend fun getMovies(moviesPage: Int): Response<MoviesPagedList> {
        return NetworkService.movieApiService.getMovies(moviesPage)
    }

    suspend fun getMovieDetails(movieId: String): Response<MovieDetails> {
        return NetworkService.movieApiService.getMovieDetails(movieId)
    }
}