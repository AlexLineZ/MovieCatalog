package com.example.moviecatalog.presentation.screen.favouritescreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.domain.state.RegistrationState
import com.example.moviecatalog.domain.usecase.GetFavoritesUseCase
import com.example.moviecatalog.domain.usecase.GetMovieDetailsUseCase
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class MovieUserMark(
    val movieElement: MovieElement,
    var userMark: Int? = null
)

class FavoriteViewModel: ViewModel() {
    private val getFavoritesUseCase = GetFavoritesUseCase()
    private val getProfileUseCase = GetProfileUseCase()
    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()

    private val _state = MutableStateFlow(arrayListOf<MovieUserMark>())
    val state: StateFlow<ArrayList<MovieUserMark>> get() = _state

    private var userId = mutableStateOf("")

    fun performData() {
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
                    _state.value = ArrayList(movieUserMarks)
                }
            }
        }
    }

    private suspend fun getProfile() {
        val result = getProfileUseCase.invoke()
        if (result.isSuccess) {
            val response = result.getOrNull()
            if (response != null) {
                userId.value = response.id
            }
        }
    }

    private suspend fun getMovieUserMark(movieElement: MovieElement): Int? {
        val result = getMovieDetailsUseCase.invoke(movieElement.id)
        if (result.isSuccess) {
            val response = result.getOrNull()
            return response?.reviews?.firstOrNull { review ->
                userId.value == review.author?.userId
            }?.rating
        }
        return null
    }
}