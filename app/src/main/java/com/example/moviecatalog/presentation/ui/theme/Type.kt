package com.example.moviecatalog.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R

val interFamily = FontFamily(
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_black, FontWeight.Black),
    Font(R.font.inter_extrabold, FontWeight.ExtraBold),
    Font(R.font.inter_thin, FontWeight.Thin),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_extralight, FontWeight.ExtraLight)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.W600,
        fontSize = 17.sp
    ),
    titleSmall = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.W600,
        fontSize = 15.sp
    ),
    labelMedium = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.W500,
        fontSize = 15.sp
    )
)

val spanStyleGray = SpanStyle (
    color = GrayColor,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    letterSpacing = 0.5.sp
)

val spanStyleAccent = SpanStyle (
    color = AccentColor,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    letterSpacing = 0.5.sp
)