package com.example.moviecatalog.presentation.screen.favouritescreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.screen.favouritescreen.components.MovieCardFavourite
import com.example.moviecatalog.presentation.screen.mainscreen.components.PullIndicator
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding
import com.example.moviecatalog.presentation.ui.theme.Values.MoreSpaceBetweenObjects
import com.example.moviecatalog.presentation.ui.theme.Values.SpaceBetweenObjects
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FavouriteScreen(viewModel: FavoriteViewModel, router: BottomBarRouter) {
    LaunchedEffect(Unit) {
        viewModel.performData()
    }

    Scaffold(
        bottomBar = {
            BottomBar(
                router = router,
                currentRoute = Routes.Favourite.route
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            FavoriteMoviesList(
                viewModel = viewModel,
                router = router
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesList(viewModel: FavoriteViewModel, router: BottomBarRouter){
    val stateList = viewModel.state.collectAsState()

    val pullState = rememberPullRefreshState(
        refreshing = stateList.value.isLoading,
        onRefresh = { viewModel.refreshData() }
    )

    val rotation = animateFloatAsState(pullState.progress * 120)

    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(BasePadding)
                .pullRefresh(pullState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.favourite),
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W700),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = BasePadding)
            )

            if (stateList.value.movieList.isEmpty()) {
                Spacer(modifier = Modifier.height(100.dp))
                EmptyFavouriteScreen()
            } else {
                LazyColumn {
                    item {
                        val chunkedItems = stateList.value.movieList.chunked(3)
                        chunkedItems.forEachIndexed { index, chunk ->
                            when (chunk.size) {
                                3 -> {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = MoreSpaceBetweenObjects),
                                        horizontalArrangement = Arrangement.spacedBy(
                                            SpaceBetweenObjects
                                        )
                                    ) {
                                        MovieCardFavourite(
                                            movie = chunk[0].movieElement,
                                            modifier = Modifier.weight(1f),
                                            onClick = { router.toMovie(chunk[0].movieElement.id) },
                                            mark = chunk[0].userMark
                                        )
                                        MovieCardFavourite(
                                            movie = chunk[1].movieElement,
                                            modifier = Modifier.weight(1f),
                                            onClick = { router.toMovie(chunk[1].movieElement.id) },
                                            mark = chunk[1].userMark
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = MoreSpaceBetweenObjects),
                                    ) {
                                        MovieCardFavourite(
                                            movie = chunk[2].movieElement,
                                            modifier = Modifier.weight(1f),
                                            onClick = { router.toMovie(chunk[2].movieElement.id) },
                                            mark = chunk[2].userMark
                                        )
                                    }
                                }

                                2 -> {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = MoreSpaceBetweenObjects),
                                        horizontalArrangement = Arrangement.spacedBy(MiddlePadding)
                                    ) {
                                        MovieCardFavourite(
                                            movie = chunk[0].movieElement,
                                            modifier = Modifier.weight(1f),
                                            onClick = { router.toMovie(chunk[0].movieElement.id) },
                                            mark = chunk[0].userMark
                                        )
                                        MovieCardFavourite(
                                            movie = chunk[1].movieElement,
                                            modifier = Modifier.weight(1f),
                                            onClick = { router.toMovie(chunk[1].movieElement.id) },
                                            mark = chunk[1].userMark
                                        )
                                    }
                                }

                                else -> {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = MoreSpaceBetweenObjects),
                                    ) {
                                        MovieCardFavourite(
                                            movie = chunk[0].movieElement,
                                            modifier = Modifier.weight(1f),
                                            onClick = { router.toMovie(chunk[0].movieElement.id) },
                                            mark = chunk[0].userMark
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        PullIndicator(
            pullState = pullState,
            rotation = rotation.value,
            refreshing = stateList.value.isLoading,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}