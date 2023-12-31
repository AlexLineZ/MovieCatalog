package com.example.moviecatalog.presentation.screen.mainscreen

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecatalog.data.model.MovieUserMark
import com.example.moviecatalog.domain.usecase.GetMovieDetailsUseCase
import com.example.moviecatalog.domain.usecase.GetMovieListUseCase
import com.example.moviecatalog.domain.usecase.GetProfileUseCase

class MoviePagingSource(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getProfileUseCase: GetProfileUseCase
) : PagingSource<Int, MovieUserMark>() {

    private var userId: String? = null

    override fun getRefreshKey(state: PagingState<Int, MovieUserMark>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUserMark> {
        val page = params.key ?: 1

        if (userId == null) {
            val profileResult = getProfileUseCase.invoke()
            if (profileResult.isSuccess) {
                val profileResponse = profileResult.getOrNull()
                userId = profileResponse?.id.toString()
            } else {
                return LoadResult.Error(Exception("Failed to load userId"))
            }
        }

        return try {
            val response = getMovieListUseCase.invoke(page)
            if (response.isSuccess) {
                val movies = response.getOrNull()?.movies ?: arrayListOf()

                val updatedMovies = movies.map { movie ->
                    val movieDetailsResponse = getMovieDetailsUseCase.invoke(movie.id)
                    if (movieDetailsResponse.isSuccess) {
                        val movieDetails = movieDetailsResponse.getOrNull()
                        val userReview = movieDetails?.reviews?.find { review ->
                            review.author?.userId == userId
                        }
                        MovieUserMark(movie, userReview?.rating)
                    } else {
                        MovieUserMark(movie)
                    }
                }

                LoadResult.Page(
                    data = updatedMovies,
                    prevKey = if (page > 1) page - 1 else null,
                    nextKey = if (updatedMovies.isNotEmpty()) page + 1 else null
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

