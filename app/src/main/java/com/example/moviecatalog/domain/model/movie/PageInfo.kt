package com.example.moviecatalog.domain.model.movie

data class PageInfo(
    var pageSize: Int,
    var pageCount: Int,
    var currentPage: Int
)
