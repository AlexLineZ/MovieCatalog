package com.example.moviecatalog.presentation.screen.moviescreen.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.presentation.ui.theme.Gray400Color

@Composable
fun MovieDetailRow(movieDetail: MovieDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = movieDetail.type,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400),
            color = Gray400Color,
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = movieDetail.value,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400),
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
    }
}