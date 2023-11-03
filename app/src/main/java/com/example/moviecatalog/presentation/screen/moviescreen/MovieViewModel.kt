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
        slogan = null,
        description = null,
        director = null,
        budget = null,
        fees = null,
        age = Constants.ZERO
    )

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<MovieDetailsResponse> get() = _state

    init {
        performDetails("f7c6a32b-a55b-4d86-a2bd-08d9b9f3d2a2")
    }

    private fun performDetails(movieId: String) {
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
            }
        }
    }
}