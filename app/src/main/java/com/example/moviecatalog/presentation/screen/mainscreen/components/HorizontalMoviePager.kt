package com.example.moviecatalog.presentation.screen.mainscreen.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.moviecatalog.data.model.MovieUserMark
import com.example.moviecatalog.presentation.ui.theme.Values.imageHeight
import com.example.moviecatalog.presentation.ui.theme.Values.pageCount
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalMoviePager(
    movies: LazyPagingItems<MovieUserMark>,
    onClick: (String) -> Unit
) {
    val state = rememberPagerState(initialPage = 0, pageCount = { pageCount })

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            state.animateScrollToPage(
                page = (state.currentPage + 1) % state.pageCount,
                animationSpec = tween(500)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
        ) {
            HorizontalPager(
                state = state,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    AsyncImage(
                        model = movies[page]?.movieElement?.poster,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                onClick = {
                                    movies[page]?.movieElement?.id?.let { onClick(it) }
                                }
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            DotIndicator(
                state = state,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}