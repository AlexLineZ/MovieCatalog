package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.data.repository.FavoriteRepository

class DeleteFavoriteMovieUseCase {
    private val favoriteRepository = FavoriteRepository()

    suspend fun invoke(movieId: String) : Result<Unit?> {
        val response = favoriteRepository.deleteFavorite(movieId)

        return try {
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}