package com.example.moviecatalog.presentation.screen.mainscreen.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.screen.favouritescreen.MovieUserMark
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalMoviePager(movies: LazyPagingItems<MovieUserMark>, onClick: (String) -> Unit) {
    val state = rememberPagerState(initialPage = 0, pageCount = { 4 })

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
                    .height(497.dp)
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
                            .clickable (
                                onClick = { movies[page]?.movieElement?.id?.let { onClick(it) } }
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(color = Color.Black.copy(alpha = 0.25f))
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    repeat(4) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                        ){
                            if(state.currentPage == index) {
                                Icon(
                                    painterResource(R.drawable.dotactive),
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    painterResource(R.drawable.dotcircle),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}