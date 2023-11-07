package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor

@Composable
fun PosterWithGradient(
    url: String,
    scrollState: LazyListState
) {
    val posterHeight = 497.dp
    val posterHeightPx = with(LocalDensity.current) {
        posterHeight.toPx()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .graphicsLayer {
                translationY = scrollState.firstVisibleItemScrollOffset / 3f
                alpha = (-1f / posterHeightPx) * scrollState.firstVisibleItemScrollOffset + 1
            }
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