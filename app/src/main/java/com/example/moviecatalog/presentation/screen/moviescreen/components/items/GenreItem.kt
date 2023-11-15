package com.example.moviecatalog.presentation.screen.moviescreen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.Values.CenterPadding
import com.example.moviecatalog.presentation.ui.theme.Values.MiddleRound

@Composable
fun GenreItem(genre: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(MiddleRound))
            .background(AccentColor)
    ) {
        Text(
            text = genre,
            color = Color.White,
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W500),
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = CenterPadding)
        )
    }
}