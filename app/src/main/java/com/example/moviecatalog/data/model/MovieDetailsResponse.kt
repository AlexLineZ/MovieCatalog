package com.example.moviecatalog.data.model

import com.example.moviecatalog.domain.model.movie.Genre
import com.example.moviecatalog.domain.model.movie.Review

data class MovieDetailsResponse(
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
