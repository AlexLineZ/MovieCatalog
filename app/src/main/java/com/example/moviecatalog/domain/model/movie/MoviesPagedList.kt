package com.example.moviecatalog.domain.model.movie

import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.domain.model.movie.PageInfo

data class MoviesPagedList (
    val movies: ArrayList<MovieElement>,
    val pageInfo: PageInfo
)