package com.example.moviecatalog.domain.model.movie


data class MoviesPagedList (
    val movies: ArrayList<MovieElement>,
    val pageInfo: PageInfo
)