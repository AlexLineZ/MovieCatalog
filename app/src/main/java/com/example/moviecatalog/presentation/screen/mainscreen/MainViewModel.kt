package com.example.moviecatalog.presentation.screen.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.domain.usecase.GetMovieDetailsUseCase
import com.example.moviecatalog.domain.usecase.GetMovieListUseCase
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val getMovieListUseCase = GetMovieListUseCase()
    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()
    private val getProfileUseCase = GetProfileUseCase()

    val movies = Pager(
        config = PagingConfig(
            pageSize = 6,
            initialLoadSize = 6
        )
    ) {
        MoviePagingSource(getMovieListUseCase, getMovieDetailsUseCase, getProfileUseCase)
    }.flow.cachedIn(viewModelScope)
}

