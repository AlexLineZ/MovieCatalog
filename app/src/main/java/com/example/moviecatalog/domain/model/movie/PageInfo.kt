package com.example.moviecatalog.domain.model.movie

data class PageInfo(
    val pageSize: Int,
    val pageCount: Int,
    val currentPage: Int
)
