package com.example.moviecatalog.data.model

import androidx.compose.ui.graphics.Color
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor

data class Mark(
    val mark: String,
    val color: Color,
    val textColor: Color = BackgroundColor
)
