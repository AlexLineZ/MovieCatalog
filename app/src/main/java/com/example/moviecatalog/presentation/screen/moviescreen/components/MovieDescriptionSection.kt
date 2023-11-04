package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor

@Composable
fun MovieDescriptionSection(
    description: String,
    state: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = description,
            fontSize = 15.sp,
            maxLines = if (state) Int.MAX_VALUE else 4,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            modifier = Modifier
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, BackgroundColor),
                        startY = size.height * 0.5f,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        if (!state) {
                            drawRect(gradient)
                        }
                    }
                }
        )

        Text(
            text = if (state) "Свернуть ▲" else "Подробнее ▼",
            fontSize = 15.sp,
            color = AccentColor,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable {
                    onClick()
                }
        )
    }
}