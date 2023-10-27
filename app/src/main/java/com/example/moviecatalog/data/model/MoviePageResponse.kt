package com.example.moviecatalog.data.model

import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.domain.model.movie.PageInfo

data class MoviePageResponse (
    var movies: ArrayList<MovieElement>,
    var pageInfo: PageInfo
)