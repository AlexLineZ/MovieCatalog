package com.example.moviecatalog.data.model

import com.example.moviecatalog.domain.model.movie.Genre
import com.example.moviecatalog.domain.model.movie.Review

data class MovieDetailsResponse(
    var id: String,
    var name: String?,
    var poster: String?,
    var year: Int,
    var country: String?,
    var genres: ArrayList<Genre>?,
    var reviews: ArrayList<Review>?,
    var time: Int,
    var slogan: String?,
    var description: String?,
    var director: String?,
    var budget: Int?,
    var fees: Int?,
    var age: Int,
)
