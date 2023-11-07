package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.domain.model.movie.Genre
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.GenreItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenresSection(genres: ArrayList<Genre>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.genres),
            fontSize = 16.sp,
            fontWeight = FontWeight.W700,
            color = Color.White,
            modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genres.forEach { genre ->
                GenreItem(
                    genre = genre.name ?: Constants.EMPTY_STRING
                )
            }
        }
    }
}
