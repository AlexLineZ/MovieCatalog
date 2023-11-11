package com.example.moviecatalog.domain.state

import com.example.moviecatalog.presentation.screen.favouritescreen.MovieUserMark

data class FavoriteState(
    val isLoading: Boolean,
    var userId: String,
    var movieList: ArrayList<MovieUserMark>
)
