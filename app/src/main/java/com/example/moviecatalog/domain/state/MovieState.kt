package com.example.moviecatalog.domain.state

import com.example.moviecatalog.domain.model.movie.MovieDetails
import com.example.moviecatalog.domain.model.review.Review

data class MovieState(
    val isLoading: Boolean,
    var movieDetails: MovieDetails,
    var userReview: Review?,
    var userId: String,
    val isDescriptionOpen: Boolean,
    var isLiked: Boolean,
    var hasUserReview: Boolean,
    val isReviewDialogOpen: Boolean,
    val isDropDownMenuOpen: Boolean,

    val movieRating: Int,
    val reviewText: String,
    var isAnonymous: Boolean
)