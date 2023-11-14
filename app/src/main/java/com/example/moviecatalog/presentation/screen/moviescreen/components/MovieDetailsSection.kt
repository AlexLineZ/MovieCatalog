package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.common.Formatter
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.MovieDetail
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.MovieDetailRow
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.MoreSpaceBetweenObjects

@Composable
fun MovieDetailsSection(
    year: Int,
    country: String?,
    slogan: String?,
    director: String?,
    budget: Int?,
    fees: Int?,
    age: Int,
    time: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = BasePadding,
                end = BasePadding,
                top = MoreSpaceBetweenObjects
            )
    ) {
        Text(
            text = stringResource(id = R.string.about_movie),
            fontSize = 16.sp,
            fontWeight = FontWeight.W700,
            color = Color.White
        )

        MovieDetailRow(
            MovieDetail(stringResource(id = R.string.genres), "$year")
        )
        MovieDetailRow(
            MovieDetail(stringResource(id = R.string.country), country?: Constants.NOTHING)
        )
        MovieDetailRow(
            MovieDetail(stringResource(id = R.string.slogan), slogan?: Constants.NOTHING)
        )
        MovieDetailRow(
            MovieDetail(stringResource(id = R.string.director), director?: Constants.NOTHING)
        )
        MovieDetailRow(
            MovieDetail(stringResource(id = R.string.budget), if (budget == null) Constants.NOTHING
            else "$${Formatter.splitNumber(budget.toString())}")
        )
        MovieDetailRow(
            MovieDetail(stringResource(id = R.string.fees), if (fees == null) Constants.NOTHING
            else "$${Formatter.splitNumber(fees.toString())}")
        )
        MovieDetailRow(
            MovieDetail(stringResource(id = R.string.age_limit), "$age+")
        )
        MovieDetailRow(
            MovieDetail(stringResource(id = R.string.time), "$time мин.")
        )
    }
}