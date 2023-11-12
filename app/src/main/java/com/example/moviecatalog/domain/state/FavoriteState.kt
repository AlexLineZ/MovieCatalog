package com.example.moviecatalog.domain.state

import com.example.moviecatalog.data.model.MovieUserMark

data class FavoriteState(
    val isLoading: Boolean,
    var userId: String,
    var movieList: ArrayList<MovieUserMark>
)
