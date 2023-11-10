package com.example.moviecatalog.presentation.screen.moviescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.model.movie.MovieDetails
import com.example.moviecatalog.domain.model.review.ReviewModify
import com.example.moviecatalog.domain.state.MovieState
import com.example.moviecatalog.domain.usecase.DeleteFavoriteMovieUseCase
import com.example.moviecatalog.domain.usecase.DeleteReviewUseCase
import com.example.moviecatalog.domain.usecase.GetFavoritesUseCase
import com.example.moviecatalog.domain.usecase.GetMovieDetailsUseCase
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import com.example.moviecatalog.domain.usecase.PostAddFavoriteMovieUseCase
import com.example.moviecatalog.domain.usecase.PostAddReviewUseCase
import com.example.moviecatalog.domain.usecase.PutReviewUseCase
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.router.LogoutRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(val router: LogoutRouter) : ViewModel() {
    private val getMovieDetailsUseCase = GetMovieDetailsUseCase()
    private val getFavoritesUseCase = GetFavoritesUseCase()
    private val postAddFavoriteMovieUseCase = PostAddFavoriteMovieUseCase()
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase()
    private val getProfileUseCase = GetProfileUseCase()
    private val postAddReviewUseCase = PostAddReviewUseCase()
    private val putReviewUseCase = PutReviewUseCase()
    private val deleteReviewUseCase = DeleteReviewUseCase()

    private val emptyMovieState = MovieDetails(
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
        userReview = null,
        isDescriptionOpen = Constants.FALSE,
        isLiked = Constants.FALSE,
        userId = Constants.EMPTY_STRING,
        hasUserReview = Constants.FALSE,
        isReviewDialogOpen = Constants.FALSE,
        isDropDownMenuOpen = Constants.FALSE,
        movieRating = 1,
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
            MovieIntent.ChangeReviewDialogOpen -> {
                _state.value = state.value.copy(
                    isReviewDialogOpen = !_state.value.isReviewDialogOpen
                )
                if (_state.value.userReview != null) {
                    _state.value = state.value.copy(
                        isAnonymous = _state.value.userReview!!.isAnonymous
                    )
                    _state.value = state.value.copy(
                        movieRating = _state.value.userReview!!.rating
                    )
                    _state.value = state.value.copy(
                        reviewText = _state.value.userReview!!.reviewText!!
                    )
                }
            }
            is MovieIntent.ClickOnFavoriteButton -> {
                if (state.value.isLiked) {
                    deleteFromFavorite(movieId = intent.movieId)
                } else {
                    addToFavorite(movieId = intent.movieId)
                }
            }
            is MovieIntent.ChangeUserReview -> {
                _state.value = state.value.copy(
                    userReview = intent.review
                )
            }
            is MovieIntent.ChangeReviewText -> {
                _state.value = state.value.copy(
                    reviewText = intent.text
                )
            }
            is MovieIntent.ChangeRating -> {
                _state.value = state.value.copy(
                    movieRating = intent.rating
                )
            }
            is MovieIntent.ChangeAnonymous -> {
                _state.value = state.value.copy(
                    isAnonymous = intent.state
                )
            }
            is MovieIntent.SendReview -> {
                if (state.value.userReview == null) {
                    addReview()
                } else {
                    editReview()
                }
            }
            is MovieIntent.DeleteReview -> {
                deleteReview()
            }
            MovieIntent.ChangeDropDownMenuOpen -> {
                _state.value = state.value.copy(
                    isDropDownMenuOpen = !_state.value.isDropDownMenuOpen
                )
            }
        }
    }

    private fun resetStateToEmpty() {
        _state.value = emptyState
    }

    fun performDetails(movieId: String) {
        resetStateToEmpty()
        viewModelScope.launch {
            processIntent(MovieIntent.ChangeIsLoading)
            val result = getMovieDetailsUseCase.invoke(movieId)
            if (result.isSuccess) {
                val response = result.getOrNull()
                response?.let {
                    _state.value.movieDetails = it
                    checkMovieIsLiked(it.id)
                    getProfile()
                }
            } else {
                userOutLogin { router.toErrorAfterOut() }
            }
            processIntent(MovieIntent.ChangeIsLoading)
        }
    }

    fun isButtonAvailable() : Boolean {
        return state.value.reviewText.isNotEmpty()
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
            } else {
                userOutLogin { router.toErrorAfterOut()  }
            }
        }
    }

    private fun deleteFromFavorite(movieId: String){
        viewModelScope.launch {
            val result = deleteFavoriteMovieUseCase.invoke(movieId)
            if (result.isSuccess){
                processIntent(MovieIntent.ChangeLiked)
            } else {
                userOutLogin { router.toErrorAfterOut()  }
            }
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            val result = getProfileUseCase.invoke()
            Log.d("RESULT", result.toString())
            if (result.isSuccess) {
                val response = result.getOrNull()
                if (response != null) {
                    _state.value.userId = response.id
                    _state.value.movieDetails.reviews?.forEach { review ->
                        if (review.author?.userId == response.id) {
                            processIntent(MovieIntent.ChangeUserReview(review))
                            processIntent(MovieIntent.ChangeReviewText(review.reviewText!!))
                            processIntent(MovieIntent.ChangeRating(review.rating))
                            _state.value.isAnonymous = review.isAnonymous
                        }
                    }
                }
            } else {
                userOutLogin { router.toErrorAfterOut()  }
            }
        }
    }

    private fun addReview() {
        val review = ReviewModify(
            reviewText = state.value.reviewText,
            rating = state.value.movieRating,
            isAnonymous = state.value.isAnonymous
        )
        Log.d("Review", review.toString())
        viewModelScope.launch {
            try {
                val result = postAddReviewUseCase.invoke(state.value.movieDetails.id, review)
                if (result.isSuccess) {
                    performDetails(state.value.movieDetails.id)
                } else if (result.isFailure){
                    userOutLogin { router.toErrorAfterOut() }
                }
            } catch (e: Exception) {
                userOutLogin { router.toErrorAfterOut() }
            }
        }
    }

    private fun deleteReview() {
        viewModelScope.launch {
            try {
                val result = deleteReviewUseCase.invoke(state.value.movieDetails.id, state.value.userReview!!.id)
                if (result.isSuccess){
                    processIntent(MovieIntent.ChangeUserReview(null))
                } else if (result.isFailure){
                    userOutLogin { router.toErrorAfterOut()  }
                }
            } catch (e: Exception) {
                userOutLogin { router.toErrorAfterOut()  }
            }
        }
    }

    private fun editReview() {
        val review = ReviewModify(
            reviewText = state.value.reviewText,
            rating = state.value.movieRating,
            isAnonymous = state.value.isAnonymous
        )

        viewModelScope.launch {
            try {
                val result = putReviewUseCase.invoke(
                    state.value.movieDetails.id,
                    state.value.userReview!!.id,
                    review
                )
                if (result.isSuccess){
                    performDetails(state.value.movieDetails.id)
                } else if (result.isFailure){
                    userOutLogin { router.toErrorAfterOut() }
                }
            } catch (e: Exception) {
                userOutLogin { router.toErrorAfterOut()  }
            }
        }
    }

    private fun userOutLogin(goTo: () -> Unit){
        NetworkService.setAuthToken(Constants.EMPTY_STRING)
        goTo()
    }
}