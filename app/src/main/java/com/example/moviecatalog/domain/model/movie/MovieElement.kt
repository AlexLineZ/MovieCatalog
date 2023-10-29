package com.example.moviecatalog.domain.model.movie


data class MovieElement (
    val id: String,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: ArrayList<Genre>?,
    val reviews: ArrayList<Review>?,
)

