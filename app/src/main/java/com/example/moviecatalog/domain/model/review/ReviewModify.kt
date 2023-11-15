package com.example.moviecatalog.domain.model.review

data class ReviewModify(
    val reviewText: String,
    val rating: Int,
    val isAnonymous: Boolean
)
