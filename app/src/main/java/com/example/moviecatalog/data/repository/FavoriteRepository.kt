package com.example.moviecatalog.data.repository

import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.model.favorite.Favorite
import retrofit2.Response

class FavoriteRepository {
    suspend fun getFavorites(): Response<Favorite> {
        return NetworkService.favoriteApiService.getFavorites()
    }

    suspend fun postFavorite(id: String): Response<Unit> {
        return NetworkService.favoriteApiService.postFavorite(id)
    }

    suspend fun deleteFavorite(id: String): Response<Unit> {
        return NetworkService.favoriteApiService.deleteFavorite(id)
    }
}