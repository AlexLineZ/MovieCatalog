package com.example.moviecatalog.data.model

import com.example.moviecatalog.domain.model.movie.MovieElement

data class MovieUserMark(
    val movieElement: MovieElement,
    var userMark: Int? = null
)
