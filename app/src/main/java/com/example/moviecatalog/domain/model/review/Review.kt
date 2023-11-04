package com.example.moviecatalog.domain.model.review

import com.example.moviecatalog.domain.model.movie.UserShort


data class Review(
    var id: String,
    var rating: Int,
    var reviewText: String?,
    var isAnonymous: Boolean,
    var createDateTime: String,
    var author: UserShort
)
