package com.example.moviecatalog.presentation.screen.mainscreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviecatalog.R
import com.example.moviecatalog.data.model.CurrentReview
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.screen.common.LoadingFullScreen
import com.example.moviecatalog.presentation.screen.mainscreen.components.HorizontalMoviePager
import com.example.moviecatalog.presentation.screen.mainscreen.components.MovieCard
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    router: BottomBarRouter,
    navBackStackEntry: NavBackStackEntry
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                router = router,
                currentRoute = Routes.HomeScreen.route
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            MovieListScreen(
                viewModel = viewModel,
                router = router,
                navBackStackEntry = navBackStackEntry
            )
        }
    }
}

@Composable
fun MovieListScreen(
    viewModel: MainViewModel,
    router: BottomBarRouter,
    navBackStackEntry: NavBackStackEntry
){
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val changes = viewModel.state.collectAsState()

    LaunchedEffect(Unit){
        navBackStackEntry.savedStateHandle.get<CurrentReview>("info")?.let {
            viewModel.changeState(it)
        }
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                when(movies.loadState.refresh) {
                    is LoadState.NotLoading -> {
                        HorizontalMoviePager(movies) {
                            router.toMovie(it)
                        }
                    }
                    is LoadState.Error -> Unit
                    LoadState.Loading -> Unit
                }
            }

            item {
                Text(
                    text = stringResource(R.string.catalog),
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W700),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(
                        top = BasePadding,
                        bottom = 3.dp,
                        start = BasePadding,
                        end = BasePadding
                    )
                )
            }

            items(
                count = movies.itemCount,
            ){ movie ->
                if (movie !in 0..3){
                    movies[movie]?.let {
                        if (it.movieElement.id == changes.value.movieId){
                            MovieCard (
                                movie = it.movieElement,
                                onClick = { router.toMovie(it.movieElement.id) },
                                userMark = changes.value.userRating,
                                anotherMark = changes.value.fullRating.toFloat()
                            )
                        } else {
                            MovieCard (
                                movie = it.movieElement,
                                onClick = { router.toMovie(it.movieElement.id) },
                                userMark = it.userMark
                            )
                        }
                    }
                }
            }

            when(movies.loadState.append) {
                is LoadState.NotLoading -> Unit
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    item {
                        LoadingFullScreen()
                    }
                }
            }

            when(movies.loadState.refresh) {
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    item {
                        LoadingFullScreen()
                    }
                }
                is LoadState.NotLoading -> Unit
            }
        }
    }
}
