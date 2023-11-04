package com.example.moviecatalog.data.network

import com.example.moviecatalog.domain.model.movie.MovieDetails
import com.example.moviecatalog.domain.model.movie.MoviesPagedList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("api/movies/{page}")
    suspend fun getMovies(@Path("page") moviesPage: Int): Response<MoviesPagedList>

    @GET("api/movies/details/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: String): Response<MovieDetails>
}