package com.example.moviecatalog.presentation.screen.moviescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.common.MarkSelector
import com.example.moviecatalog.data.model.Mark
import com.example.moviecatalog.presentation.screen.common.LoadingItem
import com.example.moviecatalog.presentation.screen.moviescreen.components.GenresSection
import com.example.moviecatalog.presentation.screen.moviescreen.components.MovieDescriptionSection
import com.example.moviecatalog.presentation.screen.moviescreen.components.MovieDetailsSection
import com.example.moviecatalog.presentation.screen.moviescreen.components.MovieReviewsSection
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor
import com.example.moviecatalog.presentation.ui.theme.BottomBarColor
import com.example.moviecatalog.presentation.ui.theme.ChipColor
import com.example.moviecatalog.presentation.ui.theme.Gray400Color
import com.example.moviecatalog.presentation.ui.theme.WhiteColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    backTo: () -> Unit,
    viewModel: MovieViewModel,
    movieId: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {  },
                navigationIcon = {
                    IconButton(onClick = { backTo() }) {
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
            val state = viewModel.state.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.performDetails(movieId)
            }

            if (state.value.isLoading) {
                LoadingItem()
            } else {
                LazyColumn {
                    item{
                        PosterWithGradient(state.value.movieDetails.poster ?: Constants.EMPTY_STRING)
                    }
                    item {
                        LabelWithButtonAndMark(
                            mark = MarkSelector.markCalculation(state.value.movieDetails.reviews ?: arrayListOf()),
                            movieName = state.value.movieDetails.name ?: Constants.EMPTY_STRING,
                            isLiked = state.value.isLiked,
                            onClickToLikeButton = { viewModel.processIntent(MovieIntent.ClickOnFavoriteButton(state.value.movieDetails.id)) }
                        )
                    }
                    item{
                        MovieDescriptionSection(
                            description = state.value.movieDetails.description ?: Constants.EMPTY_STRING,
                            state = state.value.isDescriptionOpen,
                            onClick = { viewModel.processIntent(MovieIntent.ChangeDescriptionVisibility) }

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
                            isDialogOpen = state.value.isReviewDialogOpen,
                            onClick = { viewModel.processIntent(MovieIntent.ChangeReviewDialog) }

                        )
                    }
                }
            }
        }
    )
}

@Composable
fun PosterWithGradient(url: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, BackgroundColor),
                        startY = size.height * 0.7f,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient)
                    }
                }
        )
    }
}

@Composable
fun LabelWithButtonAndMark(
    mark: Mark,
    movieName: String,
    isLiked: Boolean,
    onClickToLikeButton: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = mark.color,
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Text(
                text = mark.mark,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 14.dp, top = 4.dp, end = 14.dp, bottom = 4.dp)
            )
        }

        Text(
            text = movieName,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .background(
                    color = ChipColor,
                    shape = CircleShape
                )
                .wrapContentSize()
        ) {
            IconButton(
                onClick = { onClickToLikeButton() },
                modifier = Modifier
                    .size(40.dp),
                content = {
                    Icon(
                        imageVector = if (isLiked)
                            ImageVector.vectorResource(id = R.drawable.like_focused)
                        else
                            ImageVector.vectorResource(id = R.drawable.like_unfocused),
                        contentDescription = null,
                        tint = if (isLiked) AccentColor else WhiteColor,
                    )
                }
            )
        }
    }
}



