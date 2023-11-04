package com.example.moviecatalog.presentation.screen.moviescreen

sealed class MovieIntent {
    object ChangeIsLoading: MovieIntent()
    object ChangeDescriptionVisibility: MovieIntent()
    object ChangeLiked: MovieIntent()
    object ChangeHasUserReview: MovieIntent()
    object ChangeReviewDialog: MovieIntent()
    data class ClickOnFavoriteButton(val movieId: String): MovieIntent()
}
