package com.example.moviecatalog.domain.usecase

import android.util.Log
import com.example.moviecatalog.domain.model.movie.MovieDetails
import com.example.moviecatalog.data.repository.MovieRepository

class GetMovieDetailsUseCase {
    private val movieRepository = MovieRepository()

    suspend fun invoke(movieId: String): Result<MovieDetails?> {
        return try {
            val response = movieRepository.getMovieDetails(movieId)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Log.d("Details", response.code().toString())
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}