package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.data.repository.ReviewRepository
import com.example.moviecatalog.domain.model.review.ReviewModify

class PostAddReviewUseCase {
    private val reviewRepository = ReviewRepository()

    suspend fun invoke(movieId: String, review: ReviewModify) : Result<Unit?>{
        val response = reviewRepository.postAddReview(movieId, review)

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