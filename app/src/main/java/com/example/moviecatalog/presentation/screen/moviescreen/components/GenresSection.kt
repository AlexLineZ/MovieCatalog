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
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.CenterPadding
import com.example.moviecatalog.presentation.ui.theme.Values.LittlePadding
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenresSection(genres: ArrayList<Genre>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = BasePadding, end = BasePadding , top = BasePadding)
    ) {

        Text(
            text = stringResource(id = R.string.genres),
            fontSize = 16.sp,
            fontWeight = FontWeight.W700,
            color = Color.White,
            modifier = Modifier.padding(top = LittlePadding, bottom = CenterPadding)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(MiddlePadding),
            verticalArrangement = Arrangement.spacedBy(MiddlePadding)
        ) {
            genres.forEach { genre ->
                GenreItem(
                    genre = genre.name ?: Constants.EMPTY_STRING
                )
            }
        }
    }
}
