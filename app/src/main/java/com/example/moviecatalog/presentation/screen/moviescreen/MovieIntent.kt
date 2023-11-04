package com.example.moviecatalog.presentation.screen.moviescreen

import com.example.moviecatalog.domain.model.review.Review

sealed class MovieIntent {
    object ChangeIsLoading: MovieIntent()
    object ChangeDescriptionVisibility: MovieIntent()
    object ChangeLiked: MovieIntent()
    object ChangeHasUserReview: MovieIntent()
    object ChangeReviewDialog: MovieIntent()
    data class ChangeAnonymous(val state: Boolean): MovieIntent()
    data class ClickOnFavoriteButton(val movieId: String): MovieIntent()
    data class ChangeUserReview(val review: Review): MovieIntent()
    data class ChangeReviewText(val text: String): MovieIntent()
    data class ChangeRating(val rating: Int): MovieIntent()

    object SendReview: MovieIntent()
}
