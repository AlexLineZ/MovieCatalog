package com.example.moviecatalog.data.repository

import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.model.review.ReviewModify
import retrofit2.Response

class ReviewRepository {
    suspend fun postAddReview(movieId: String, review: ReviewModify): Response<Unit> {
        return NetworkService.reviewApiService.postAddReview(movieId, review)
    }

    suspend fun putReview(movieId: String, id: String): Response<Unit> {
        return NetworkService.reviewApiService.putReview(movieId, id)
    }

    suspend fun deleteReview(movieId: String, id: String): Response<Unit> {
        return NetworkService.reviewApiService.deleteReview(movieId, id)
    }
}