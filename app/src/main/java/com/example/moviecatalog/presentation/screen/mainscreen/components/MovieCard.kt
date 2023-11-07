package com.example.moviecatalog.presentation.screen.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviecatalog.common.MarkSelector
import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.presentation.ui.theme.ChipColor


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieCard(movie: MovieElement, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .clickable (
                onClick = onClick
            )
    ) {

        Box {
            AsyncImage(
                model = movie.poster,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.Yellow),
                contentScale = ContentScale.FillBounds
            )

            if (movie.reviews != null) {
                val mark = MarkSelector.markCalculation(movie.reviews)
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .align(Alignment.TopStart)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(mark.color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = mark.mark,
                            style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.W700),
                            color = Color.Black,
                            modifier = Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 2.dp
                            )
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
        ) {
            movie.name?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
            }

            Text(
                text = movie.year.toString() + " • " + movie.country,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W400),
                modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                movie.genres?.forEach { genre ->
                    genre.name?.let { Chip(text = it) }
                }
            }
        }
    }
}