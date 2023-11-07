package com.example.moviecatalog.presentation.screen.favouritescreen

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.screen.favouritescreen.components.MovieCardFavourite
import com.example.moviecatalog.presentation.screen.mainscreen.MovieListScreen

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


@Composable
fun FavoriteMoviesList(viewModel: FavoriteViewModel, router: BottomBarRouter){
    val stateList = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.favourite),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (stateList.value.isEmpty()) {
            Spacer(modifier = Modifier.height(64.dp))
            EmptyFavouriteScreen()
        }

        else {
            LazyColumn {
                item {
                    val chunkedItems = stateList.value.chunked(3)
                    chunkedItems.forEachIndexed { index, chunk ->
                        when (chunk.size) {
                            3 -> {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    MovieCardFavourite(
                                        movie = chunk[0],
                                        modifier = Modifier.weight(1f),
                                        onClick = { router.toMovie(chunk[0].id) }
                                    )
                                    MovieCardFavourite(
                                        movie = chunk[1],
                                        modifier = Modifier.weight(1f),
                                        onClick = { router.toMovie(chunk[1].id) }
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                ) {
                                    MovieCardFavourite(
                                        movie = chunk[2],
                                        modifier = Modifier.weight(1f),
                                        onClick = { router.toMovie(chunk[2].id) }
                                    )
                                }
                            }
                            2 -> {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    MovieCardFavourite(
                                        movie = chunk[0],
                                        modifier = Modifier.weight(1f),
                                        onClick = { router.toMovie(chunk[0].id) }
                                    )
                                    MovieCardFavourite(
                                        movie = chunk[1],
                                        modifier = Modifier.weight(1f),
                                        onClick = { router.toMovie(chunk[1].id) }
                                    )
                                }
                            }
                            else -> {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                ) {
                                    MovieCardFavourite(
                                        movie = chunk[0],
                                        modifier = Modifier.weight(1f),
                                        onClick = { router.toMovie(chunk[0].id) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}