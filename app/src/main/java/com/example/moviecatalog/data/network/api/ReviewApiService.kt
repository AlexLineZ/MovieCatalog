package com.example.moviecatalog.data.network.api

import com.example.moviecatalog.domain.model.review.ReviewModify
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReviewApiService {
    @POST("api/movie/{movieId}/review/add")
    suspend fun postAddReview(
        @Path("movieId") movieId: String,
        @Body review: ReviewModify
    ): Response<Unit>

    @PUT("api/movie/{movieId}/review/{id}/edit")
    suspend fun putReview(
        @Path("movieId") movieId: String,
        @Path("id") id: String,
        @Body review: ReviewModify
    ): Response<Unit>

    @DELETE("api/movie/{movieId}/review/{id}/delete")
    suspend fun deleteReview(
        @Path("movieId") movieId: String,
        @Path("id") id: String
    ): Response<Unit>
}