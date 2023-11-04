package com.example.moviecatalog.presentation.screen.mainscreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimatedDotIndicator(
    count: Int,
    pagerState: PagerState,
) {
    val circleSpacing = 8.dp
    val circleSize = 10.dp
    val innerCircle = 7.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Transparent, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            val distance = (circleSize + circleSpacing).toPx()

            val centerX = size.width / 2
            val centerY = size.height / 2

            val totalWidth = distance * count
            val startX = centerX - (totalWidth / 2) + (circleSize / 2).toPx()

            repeat(count) {
                val pageOffset =
                    (pagerState.currentPage - it) + pagerState.currentPageOffsetFraction

                val scale = 1f.coerceAtMost(pageOffset.absoluteValue)

                val x = startX + (it * distance)
                val circleCenter = Offset(x, centerY)
                val radius = circleSize.toPx() / 2
                val innerRadius = (innerCircle.toPx() * scale) / 2

                drawCircle(
                    color = Color.White,
                    center = circleCenter,
                    radius = radius
                )

                drawCircle(
                    color = Color.Gray,
                    center = circleCenter,
                    radius = innerRadius
                )
            }
        }
    }
}