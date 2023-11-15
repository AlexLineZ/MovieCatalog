package com.example.moviecatalog.presentation.screen.mainscreen.components

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
import com.example.moviecatalog.presentation.ui.theme.ChipColor
import com.example.moviecatalog.presentation.ui.theme.Values.LittleRound
import com.example.moviecatalog.presentation.ui.theme.Values.MicroPadding
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding

@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(LittleRound))
            .background(ChipColor)
    ) {
        Text(
            text = text,
            color = Color.White,
            style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.W400),
            modifier = Modifier
                .padding(
                    horizontal = MiddlePadding,
                    vertical = MicroPadding
                )
        )
    }
}
