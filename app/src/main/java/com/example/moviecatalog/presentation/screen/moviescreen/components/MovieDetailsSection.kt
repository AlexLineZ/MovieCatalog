package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.common.Formatter
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.MovieDetail
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.MovieDetailRow

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
            .padding(16.dp)
    ) {
        Text(
            text = "О фильме",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        MovieDetailRow(MovieDetail("Год", "$year"))
        MovieDetailRow(MovieDetail("Страна", country?: Constants.NOTHING))
        MovieDetailRow(MovieDetail("Слоган", slogan?: Constants.NOTHING))
        MovieDetailRow(MovieDetail("Режиссёр", director?: Constants.NOTHING))
        MovieDetailRow(MovieDetail("Бюджет", if (budget == null) Constants.NOTHING
            else "$${Formatter.splitNumber(budget.toString())}"))
        MovieDetailRow(MovieDetail("Сборы в мире", if (fees == null) Constants.NOTHING
            else "$${Formatter.splitNumber(fees.toString())}"))
        MovieDetailRow(MovieDetail("Возраст", "$age+"))
        MovieDetailRow(MovieDetail("Время", "$time мин"))
    }
}