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
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor
import com.example.moviecatalog.presentation.ui.theme.BottomBarColor
import com.example.moviecatalog.presentation.ui.theme.ChipColor

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
            val isLoading = viewModel.isLoading.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.performDetails(movieId)
            }

            if (isLoading.value) {
                LoadingItem()
            } else {
                LazyColumn {
                    item{
                        PosterWithGradient(state.value.poster ?: Constants.EMPTY_STRING)
                    }
                    item {
                        LabelWithButtonAndMark(
                            mark = MarkSelector.markCalculation(state.value.reviews ?: arrayListOf()),
                            movieName = state.value.name ?: Constants.EMPTY_STRING
                        )
                    }
                    item{
                        MovieDescriptionSection(
                            description = state.value.description ?: Constants.EMPTY_STRING
                        )
                    }
                    item{
                        GenresSection(genres = state.value.genres ?: arrayListOf())
                    }
                    item{
                        MovieDetailsSection(
                            year = state.value.year,
                            country = state.value.country,
                            slogan = state.value.tagline,
                            director = state.value.director,
                            budget = state.value.budget,
                            fees = state.value.fees,
                            age = state.value.ageLimit,
                            time = state.value.time
                        )
                    }
                    item{
                        MovieReviewsSection(state.value.reviews)
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
fun LabelWithButtonAndMark(mark: Mark, movieName: String){
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
                onClick = { },
                modifier = Modifier
                    .size(40.dp),
                content = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.like_unfocused),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        }
    }
}



