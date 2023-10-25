package com.example.moviecatalog.presentation.screen.mainscreen

import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.screen.BottomBarScreen
import kotlin.math.absoluteValue

@Composable
fun MainScreen() {
    HalfScreenPager()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HalfScreenPager() {
    val state = rememberPagerState { 4 }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            HorizontalPager(
                state = state,
                modifier = Modifier
                    .fillMaxWidth()
            ) { page ->
                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(text = page.toString(), fontSize = 32.sp)
                }
            }
        }

        DotIndicator(count = 4, pagerState = state)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicator(
    count: Int,
    pagerState: PagerState,
) {
    val circleSpacing = 8.dp
    val circleSize = 12.dp
    val innerCircle = 8.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier) {
            val distance = (circleSize + circleSpacing).toPx()

            val centerX = size.width / 2
            val centerY = size.height / 2

            val totalWidth = distance * count
            val startX = centerX - (totalWidth / 2) + (circleSize / 2).toPx()

            repeat(count) {
                val pageOffset = (pagerState.currentPage - it) + pagerState.currentPageOffsetFraction

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