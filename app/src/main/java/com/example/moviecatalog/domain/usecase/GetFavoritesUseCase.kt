package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.data.repository.FavoriteRepository
import com.example.moviecatalog.domain.model.favorite.Favorite

class GetFavoritesUseCase {
    private val favoriteRepository = FavoriteRepository()

    suspend fun invoke(): Result<Favorite?> {
        return try {
            val response = favoriteRepository.getFavorites()
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