package com.example.moviecatalog.presentation.screen.moviescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.navigation.BackHandler
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.common.MarkSelector
import com.example.moviecatalog.data.model.CurrentReview
import com.example.moviecatalog.presentation.screen.common.LoadingFullScreen
import com.example.moviecatalog.presentation.screen.moviescreen.components.GenresSection
import com.example.moviecatalog.presentation.screen.moviescreen.components.MovieDescriptionSection
import com.example.moviecatalog.presentation.screen.moviescreen.components.MovieDetailsSection
import com.example.moviecatalog.presentation.screen.moviescreen.components.MovieReviewsSection
import com.example.moviecatalog.presentation.screen.moviescreen.components.PosterWithGradient
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.LabelWithButtonAndMark
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.LikeButton
import com.example.moviecatalog.presentation.ui.theme.BottomBarColor
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.SpaceBetweenObjects

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    navController: NavHostController,
    viewModel: MovieViewModel,
    movieId: String
) {
    val state = viewModel.state.collectAsState()
    BackHandler {
        navController.popBackStack()
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.set("info", CurrentReview(
                movieId = movieId,
                userRating = state.value.userReview?.rating,
                fullRating = MarkSelector.markCalculation(
                    state.value.movieDetails.reviews ?: arrayListOf()).mark
            ))
    }
    val showName = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = SpaceBetweenObjects)
                    ) {
                        if (showName.value) {
                            Spacer(modifier = Modifier.weight(0.5f))
                            Text(
                                text = state.value.movieDetails.name ?: Constants.EMPTY_STRING,
                                fontWeight = FontWeight.W700,
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .widthIn(max = 250.dp)
                                    .padding(end = BasePadding)
                            )
                            Spacer(modifier = Modifier.weight(0.5f))
                            LikeButton(
                                isLiked = state.value.isLiked,
                                onClickToLikeButton = {
                                    viewModel.processIntent(MovieIntent.ChangeLiked)
                                }
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("info", CurrentReview(
                                movieId = movieId,
                                userRating = state.value.userReview?.rating,
                                fullRating = MarkSelector.markCalculation(
                                    state.value.movieDetails.reviews ?: arrayListOf()).mark
                            ))
                    }) {
                        Icon (
                            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BottomBarColor
                )
            )
        },

        content = {
            LaunchedEffect(Unit) {
                viewModel.performDetails(movieId)
            }

            val lazyListState = rememberLazyListState()

            LaunchedEffect(lazyListState) {
                snapshotFlow { lazyListState.firstVisibleItemIndex }
                    .collect { firstVisibleIndex ->
                        showName.value = firstVisibleIndex > 1
                    }
            }

            if (state.value.isLoading) {
                LoadingFullScreen()
            } else {
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.padding(it)
                ) {
                    item{
                        PosterWithGradient(
                            url = state.value.movieDetails.poster ?: Constants.EMPTY_STRING,
                            scrollState = lazyListState
                        )
                    }
                    item {
                        LabelWithButtonAndMark(
                            mark = MarkSelector.markCalculation(
                                state.value.movieDetails.reviews ?: arrayListOf()),
                            movieName = state.value.movieDetails.name ?: Constants.EMPTY_STRING,
                            isLiked = state.value.isLiked,
                            onClickToLikeButton = {
                                viewModel.processIntent(
                                    MovieIntent.ClickOnFavoriteButton(state.value.movieDetails.id)
                                )
                            }
                        )
                    }
                    item{
                        MovieDescriptionSection(
                            description = state.value.movieDetails.description
                                ?: Constants.EMPTY_STRING,
                            state = state.value.isDescriptionOpen,
                            onClick = {
                                viewModel.processIntent(MovieIntent.ChangeDescriptionVisibility)
                            }
                        )
                    }
                    item{
                        GenresSection(genres = state.value.movieDetails.genres ?: arrayListOf())
                    }
                    item{
                        MovieDetailsSection(
                            year = state.value.movieDetails.year,
                            country = state.value.movieDetails.country,
                            slogan = state.value.movieDetails.tagline,
                            director = state.value.movieDetails.director,
                            budget = state.value.movieDetails.budget,
                            fees = state.value.movieDetails.fees,
                            age = state.value.movieDetails.ageLimit,
                            time = state.value.movieDetails.time
                        )
                    }
                    item{
                        MovieReviewsSection(
                            list = state.value.movieDetails.reviews,
                            state = state.value,
                            onClickDialog = {
                                viewModel.processIntent(MovieIntent.ChangeReviewDialogOpen)
                            },
                            onSaveClick = {
                                viewModel.processIntent(MovieIntent.SendReview)
                            },
                            onRatingSelected = {
                                viewModel.processIntent(MovieIntent.ChangeRating(it))
                            },
                            onAnonymousCheckedChanged = {
                                viewModel.processIntent(MovieIntent.ChangeAnonymous(it))
                            },
                            onReviewTextChanged = {
                                viewModel.processIntent(MovieIntent.ChangeReviewText(it))
                            },
                            onDeleteClick = {
                                viewModel.processIntent(MovieIntent.DeleteReview)
                            },
                            onDropClick = {
                                viewModel.processIntent(MovieIntent.ChangeDropDownMenuOpen)
                            },
                            isButtonAvailable = viewModel.isButtonAvailable()
                        )
                    }
                }
            }
        }
    )
}



