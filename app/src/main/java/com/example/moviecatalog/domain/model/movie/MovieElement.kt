package com.example.moviecatalog.domain.model.movie

import com.example.moviecatalog.domain.model.review.Review


data class MovieElement (
    val id: String,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: ArrayList<Genre>?,
    val reviews: ArrayList<Review>?,
)

