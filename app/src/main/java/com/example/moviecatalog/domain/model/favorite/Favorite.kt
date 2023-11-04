package com.example.moviecatalog.domain.model.favorite

import com.example.moviecatalog.domain.model.movie.MovieElement

data class Favorite(
    val movies: ArrayList<MovieElement>
)
