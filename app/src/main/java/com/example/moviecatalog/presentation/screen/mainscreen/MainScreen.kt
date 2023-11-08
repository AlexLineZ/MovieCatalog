package com.example.moviecatalog.presentation.screen.mainscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.screen.common.LoadingFullScreen
import com.example.moviecatalog.presentation.screen.common.LoadingItem
import com.example.moviecatalog.presentation.screen.common.PullIndicator
import com.example.moviecatalog.presentation.screen.mainscreen.components.HorizontalMoviePager
import com.example.moviecatalog.presentation.screen.mainscreen.components.MovieCard
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.pullRefreshIndicatorTransform
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel, router: BottomBarRouter) {
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
                router = router
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(viewModel: MainViewModel, router: BottomBarRouter){
    val movies = viewModel.movies.collectAsLazyPagingItems()

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        movies.refresh()
        delay(1500)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val rotation = animateFloatAsState(state.progress * 120)

    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(state)
    ) {
        if (!refreshing){
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
                            MovieCard (
                                movie = it.movieElement,
                                onClick = { router.toMovie(it.movieElement.id) },
                                userMark = it.userMark
                            )
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
        else {
            LoadingFullScreen()
        }

        PullIndicator(
            pullState = state,
            rotation = rotation.value,
            refreshing = refreshing,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
