package com.example.moviecatalog.presentation.screen.moviescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.model.MovieDetailsResponse
import com.example.moviecatalog.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()

    private val emptyState = MovieDetailsResponse(
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

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<MovieDetailsResponse> get() = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading


    fun performDetails(movieId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = getMovieDetailsUseCase.invoke(movieId)
                if (result.isSuccess) {
                    val response = result.getOrNull()
                    response?.let {
                        _state.value = it
                    }
                } else {
                    Log.d("Mem", "hahaha")
                }
            } catch (e: Exception) {
                Log.d("ERROR", e.message.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }
}