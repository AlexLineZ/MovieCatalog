package com.example.moviecatalog.data.network.api

import com.example.moviecatalog.domain.model.favorite.Favorite
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteApiService {
    @GET("api/favorites")
    suspend fun getFavorites(): Response<Favorite>

    @POST("api/favorites/{id}/add")
    suspend fun postFavorite(@Path("id") id: String): Response<Unit>

    @DELETE("api/favorites/{id}/delete")
    suspend fun deleteFavorite(@Path("id") id: String): Response<Unit>
}