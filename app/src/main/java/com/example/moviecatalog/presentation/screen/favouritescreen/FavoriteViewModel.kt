package com.example.moviecatalog.presentation.screen.favouritescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.model.MovieUserMark
import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.domain.state.FavoriteState
import com.example.moviecatalog.domain.usecase.GetFavoritesUseCase
import com.example.moviecatalog.domain.usecase.GetMovieDetailsUseCase
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel() {
    private val getFavoritesUseCase = GetFavoritesUseCase()
    private val getProfileUseCase = GetProfileUseCase()
    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()


    private val emptyState = FavoriteState(
        isLoading = Constants.FALSE,
        userId = Constants.EMPTY_STRING,
        movieList = arrayListOf()
    )

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<FavoriteState> get() = _state


    fun processIntent(intent: FavoriteIntent) {
        when(intent) {
            FavoriteIntent.UpdateLoading -> {
                _state.value = state.value.copy(
                    isLoading = !_state.value.isLoading
                )
            }
        }

    }

    fun performData() {
        if (_state.value.isLoading) {
            _state.value.isLoading = Constants.FALSE
        }
        viewModelScope.launch {
            getProfile()
            val result = getFavoritesUseCase.invoke()
            if (result.isSuccess) {
                val response = result.getOrNull()
                response?.let {
                    val movieUserMarks = it.movies.map { movie ->
                        val userMark = getMovieUserMark(movie)
                        MovieUserMark(movie, userMark)
                    }
                    _state.value.movieList = ArrayList(movieUserMarks)
                }
            } else {
                _state.value.movieList = arrayListOf()
            }
        }
    }

    fun refreshData() {
        processIntent(FavoriteIntent.UpdateLoading)
        viewModelScope.launch {
            val result = getFavoritesUseCase.invoke()
            if (result.isSuccess) {
                val response = result.getOrNull()
                response?.let {
                    val movieUserMarks = it.movies.map { movie ->
                        val userMark = getMovieUserMark(movie)
                        MovieUserMark(movie, userMark)
                    }
                    _state.value.movieList = ArrayList(movieUserMarks)
                }
            } else {
                _state.value.movieList = arrayListOf()
            }
            delay(500)
            processIntent(FavoriteIntent.UpdateLoading)
        }
    }

    private suspend fun getProfile() {
        val result = getProfileUseCase.invoke()
        if (result.isSuccess) {
            val response = result.getOrNull()
            if (response != null) {
                _state.value.userId = response.id
            }
        }
    }

    private suspend fun getMovieUserMark(movieElement: MovieElement): Int? {
        val result = getMovieDetailsUseCase.invoke(movieElement.id)
        if (result.isSuccess) {
            val response = result.getOrNull()
            return response?.reviews?.firstOrNull { review ->
                _state.value.userId == review.author?.userId
            }?.rating
        }
        return null
    }
}