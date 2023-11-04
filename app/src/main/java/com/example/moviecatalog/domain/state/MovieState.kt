package com.example.moviecatalog.domain.state

import com.example.moviecatalog.data.model.MovieDetailsResponse

data class MovieState(
    val isLoading: Boolean,
    var movieDetails: MovieDetailsResponse,
    val isDescriptionOpen: Boolean,
    var isLiked: Boolean,
    var hasUserReview: Boolean,
    val isReviewDialogOpen: Boolean,

    val movieRating: Int,
    val reviewText: String,
    var isAnonymous: Boolean
)