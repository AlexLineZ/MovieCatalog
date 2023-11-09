package com.example.moviecatalog.presentation.screen.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.model.CurrentReview
import com.example.moviecatalog.domain.state.MovieState
import com.example.moviecatalog.domain.usecase.GetMovieDetailsUseCase
import com.example.moviecatalog.domain.usecase.GetMovieListUseCase
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val getMovieListUseCase = GetMovieListUseCase()
    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()
    private val getProfileUseCase = GetProfileUseCase()

    private val emptyState = CurrentReview(
        movieId = Constants.EMPTY_STRING,
        userRating = null
    )

    private var _state = MutableStateFlow(emptyState)
    val state: StateFlow<CurrentReview> get() = _state

    val movies = Pager(
        config = PagingConfig(
            pageSize = 6,
            initialLoadSize = 6
        )
    ) {
        MoviePagingSource(getMovieListUseCase, getMovieDetailsUseCase, getProfileUseCase)
    }.flow.cachedIn(viewModelScope)

    fun changeState(reviewState: CurrentReview) {
        _state.value = reviewState
    }
}

