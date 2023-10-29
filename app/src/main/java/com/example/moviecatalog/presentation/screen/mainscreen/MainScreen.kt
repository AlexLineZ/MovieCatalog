package com.example.moviecatalog.presentation.screen.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.screen.common.LoadingItem
import com.example.moviecatalog.presentation.screen.mainscreen.components.HorizontalMoviePager
import com.example.moviecatalog.presentation.screen.mainscreen.components.MovieCard

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val movies = viewModel.movies.collectAsLazyPagingItems()

    LazyColumn {
        item {
            when(movies.loadState.refresh) {
                is LoadState.NotLoading -> {
                    HorizontalMoviePager(movies)
                }
                is LoadState.Error -> Unit
                LoadState.Loading -> Unit
            }
        }

        item {
            Text(
                text = stringResource(R.string.catalog),
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(
                    top = 16.dp,
                    bottom = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )
        }

        items(
            count = movies.itemCount,
        ){ movie ->
            movies[movie]?.let { MovieCard(it) }
        }

        when(movies.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Error -> Unit
            LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
        }

        when(movies.loadState.refresh) {
            is LoadState.Error -> Unit
            LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            is LoadState.NotLoading -> Unit
        }
    }
}


