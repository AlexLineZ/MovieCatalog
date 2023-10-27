package com.example.moviecatalog.presentation.screen.mainscreen

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.domain.usecase.GetMovieListUseCase

class MoviePagingSource(private val getMovieListUseCase: GetMovieListUseCase) : PagingSource<Int, MovieElement>() {

    override fun getRefreshKey(state: PagingState<Int, MovieElement>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieElement> {
        val page = params.key ?: 1
        val pageSize: Int = params.loadSize

        return try {
            val response = getMovieListUseCase.invoke(page)
            if (response.isSuccess) {
                val movies = response.getOrNull()?.movies ?: arrayListOf()
                LoadResult.Page(
                    data = movies,
                    prevKey = if (page > 1) page - 1 else null,
                    nextKey = if (movies.isNotEmpty()) page + 1 else null
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

