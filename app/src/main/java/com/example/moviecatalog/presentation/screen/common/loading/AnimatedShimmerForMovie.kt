package com.example.moviecatalog.presentation.screen.common.loading

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.Values
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.CenterPadding
import com.example.moviecatalog.presentation.ui.theme.Values.LittleRound

@Composable
fun AnimatedShimmerForMovie() {
    val shimmerColors = listOf(
        AccentColor.copy(alpha = 0.6f),
        AccentColor.copy(alpha = 0.2f),
        AccentColor.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShimmerSquare(brush = brush)
        ShimmerGridItem(brush = brush)
    }
}

@Composable
fun ShimmerSquare(brush: Brush) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Values.imageHeight)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Values.imageHeight)
                .background(brush)
        )
    }
}

@Composable
fun ShimmerGridItem(brush: Brush) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BasePadding),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(
            modifier = Modifier
                .height(70.dp)
                .clip(RoundedCornerShape(LittleRound))
                .fillMaxWidth()
                .background(brush)
        )
        Spacer(modifier = Modifier.padding(CenterPadding))
        Spacer(
            modifier = Modifier
                .height(100.dp)
                .clip(RoundedCornerShape(LittleRound))
                .fillMaxWidth()
                .background(brush)
        )
        Spacer(modifier = Modifier.padding(CenterPadding))
        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(LittleRound))
                .fillMaxWidth(0.3f)
                .height(20.dp)
                .background(brush)
        )
        Spacer(modifier = Modifier.padding(3.dp))
        Spacer(
            modifier = Modifier
                .height(70.dp)
                .clip(RoundedCornerShape(LittleRound))
                .fillMaxWidth()
                .background(brush)
        )
    }
}