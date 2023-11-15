package com.example.moviecatalog.domain.model.movie

import com.example.moviecatalog.domain.model.movie.Genre
import com.example.moviecatalog.domain.model.review.Review

data class MovieDetails(
    val id: String,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: ArrayList<Genre>?,
    val reviews: ArrayList<Review>?,
    val time: Int,
    val tagline: String?,
    val description: String?,
    val director: String?,
    val budget: Int?,
    val fees: Int?,
    val ageLimit: Int,
)
