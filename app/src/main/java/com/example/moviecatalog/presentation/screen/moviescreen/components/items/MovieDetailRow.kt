package com.example.moviecatalog.presentation.screen.moviescreen.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.MovieDetail
import com.example.moviecatalog.presentation.ui.theme.GrayTextColor

@Composable
fun MovieDetailRow(movieDetail: MovieDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = movieDetail.type,
            fontSize = 14.sp,
            color = GrayTextColor,
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = movieDetail.value,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
    }
}