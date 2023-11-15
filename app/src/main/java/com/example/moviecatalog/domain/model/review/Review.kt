package com.example.moviecatalog.domain.model.review

import com.example.moviecatalog.domain.model.movie.UserShort


data class Review(
    val id: String,
    val rating: Int,
    val reviewText: String?,
    val isAnonymous: Boolean,
    val createDateTime: String,
    val author: UserShort
)
