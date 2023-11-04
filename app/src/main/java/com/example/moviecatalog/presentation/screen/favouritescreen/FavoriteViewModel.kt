package com.example.moviecatalog.presentation.screen.favouritescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.domain.state.RegistrationState
import com.example.moviecatalog.domain.usecase.GetFavoritesUseCase
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel() {
    private val getFavoritesUseCase = GetFavoritesUseCase()

    private val _state = MutableStateFlow(arrayListOf<MovieElement>())
    val state: StateFlow<ArrayList<MovieElement>> get() = _state

    fun performData(){
        viewModelScope.launch {
            val result = getFavoritesUseCase.invoke()
            if (result.isSuccess) {
                val response = result.getOrNull()
                response?.let {
                    _state.value = it.movies
                }
                Log.d("FAVORITE", _state.value.toString())
            }
        }
    }
}