package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.data.repository.ReviewRepository

class PutReviewUseCase {
    private val reviewRepository = ReviewRepository()

    suspend fun invoke(movieId: String, id: String) : Result<Unit?>{
        val response = reviewRepository.putReview(movieId, id)

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