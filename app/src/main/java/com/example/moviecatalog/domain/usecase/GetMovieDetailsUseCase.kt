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
                Log.d("Movie", response.code().toString())
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.d("MovieExc", e.toString())
            Result.failure(e)
        }
    }
}