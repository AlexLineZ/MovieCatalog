package com.example.moviecatalog.domain.usecase

import android.util.Log
import com.example.moviecatalog.domain.model.movie.MoviesPagedList
import com.example.moviecatalog.data.repository.MovieRepository

class GetMovieListUseCase() {
    private val movieRepository = MovieRepository()

    suspend fun invoke(moviesPage: Int): Result<MoviesPagedList?> {
        return try {
            val response = movieRepository.getMovies(moviesPage)
            return if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}