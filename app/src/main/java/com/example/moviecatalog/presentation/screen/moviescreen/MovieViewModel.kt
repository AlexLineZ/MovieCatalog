package com.example.moviecatalog.presentation.screen.moviescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.model.MovieDetailsResponse
import com.example.moviecatalog.domain.state.MovieState
import com.example.moviecatalog.domain.usecase.GetFavoritesUseCase
import com.example.moviecatalog.domain.usecase.GetMovieDetailsUseCase
import com.example.moviecatalog.domain.usecase.PostAddFavoriteMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()
    private val getFavoritesUseCase = GetFavoritesUseCase()
    private val postAddFavoriteMovieUseCase = PostAddFavoriteMovieUseCase()

    private val emptyMovieState = MovieDetailsResponse(
        id = Constants.EMPTY_STRING,
        name = null,
        poster = null,
        year = Constants.ZERO,
        country = null,
        genres = null,
        reviews = null,
        time = Constants.ZERO,
        tagline = null,
        description = null,
        director = null,
        budget = null,
        fees = null,
        ageLimit = Constants.ZERO
    )

    private val emptyState = MovieState(
        isLoading = Constants.FALSE,
        movieDetails = emptyMovieState,
        isDescriptionOpen = Constants.FALSE,
        isLiked = Constants.FALSE,
        hasUserReview = Constants.FALSE,
        isReviewDialogOpen = Constants.FALSE,
        movieRating = Constants.ZERO,
        reviewText = Constants.EMPTY_STRING,
        isAnonymous = Constants.FALSE
    )

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<MovieState> get() = _state

    fun processIntent(intent: MovieIntent){
        when(intent){
            MovieIntent.ChangeDescriptionVisibility -> {
                _state.value = state.value.copy(
                    isDescriptionOpen = !_state.value.isDescriptionOpen
                )
            }

            MovieIntent.ChangeHasUserReview -> {
                _state.value = state.value.copy(
                    hasUserReview = !_state.value.hasUserReview
                )
            }

            MovieIntent.ChangeIsLoading -> {
                _state.value = state.value.copy(
                    isLoading = !_state.value.isLoading
                )
            }

            MovieIntent.ChangeLiked -> {
                _state.value = state.value.copy(
                    isLiked = !_state.value.isLiked
                )
            }
            MovieIntent.ChangeReviewDialog -> {
                _state.value = state.value.copy(
                    isReviewDialogOpen = !_state.value.isReviewDialogOpen
                )
            }

            is MovieIntent.ClickOnFavoriteButton -> {
                if (state.value.isLiked) {

                } else {
                    addToFavorite(movieId = intent.movieId)
                }
            }
        }
    }

    private fun resetStateToEmpty() {
        _state.value = emptyState
    }

    fun performDetails(movieId: String) {
        resetStateToEmpty()
        processIntent(MovieIntent.ChangeIsLoading)
        viewModelScope.launch {
            val result = getMovieDetailsUseCase.invoke(movieId)
            if (result.isSuccess) {
                val response = result.getOrNull()
                response?.let {
                    _state.value.movieDetails = it
                    checkMovieIsLiked(it.id)
                }
            }
            processIntent(MovieIntent.ChangeIsLoading)
        }
    }

    private fun checkMovieIsLiked(movieId: String) {
        viewModelScope.launch {
            val result = getFavoritesUseCase.invoke()
            if (result.isSuccess){
                val response = result.getOrNull()
                response?.movies?.forEach { item ->
                    if (item.id == movieId){
                        processIntent(MovieIntent.ChangeLiked)
                    }
                }
            }
        }
    }

    private fun addToFavorite(movieId: String){
        viewModelScope.launch {
            val result = postAddFavoriteMovieUseCase.invoke(movieId)
            if (result.isSuccess){
                processIntent(MovieIntent.ChangeLiked)
            }
        }
    }
}