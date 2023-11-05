package com.example.moviecatalog.domain.usecase

import android.util.Log
import com.example.moviecatalog.data.repository.ReviewRepository

class DeleteReviewUseCase {
    private val reviewRepository = ReviewRepository()

    suspend fun invoke(movieId: String, id: String) : Result<Unit?>{
        val response = reviewRepository.deleteReview(movieId, id)

        return try {
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Log.d("DeleteResult", "Error: ${response.code()}")
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.d("DeleteResult2", e.toString())
            Result.failure(e)
        }
    }
}