package com.example.moviecatalog.presentation.screen.mainscreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.ChipColor
import com.example.moviecatalog.presentation.ui.theme.MiddleMarkColor
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@Composable
fun MainScreen() {
    LazyColumn {
        item {
            HalfScreenPager()
            Text(
                text = stringResource(R.string.catalog),
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            )
            repeat(3){
                MovieCard()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HalfScreenPager() {
    val state = rememberPagerState { 4 }

    val images = listOf(
        R.drawable.position_1,
        R.drawable.position_2,
        R.drawable.position_3,
        R.drawable.position_4
    )


    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            state.animateScrollToPage((state.currentPage + 1) % state.pageCount)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
        ) {
            HorizontalPager(
                state = state,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(497.dp)
            ) { page ->
                val imageRes = images.getOrNull(page) ?: R.drawable.position_1
                val image = painterResource(id = imageRes)
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(4.dp),
            ) {
                DotIndicator(count = 4, pagerState = state)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieCard() {
    val genres = listOf("драма", "комедия", "криминал", "боевик", "фантастика")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {

        Box {
            Image(
                painter = painterResource(R.drawable.position_2),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Yellow),
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.TopStart)
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MiddleMarkColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "6.0",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                        )
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Дюна",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "2013 • Россия",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                genres.forEach { genre ->
                    Chip(text = genre)
                }
            }
        }
    }
}



@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(ChipColor)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 13.sp,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicator(
    count: Int,
    pagerState: PagerState,
) {
    val circleSpacing = 8.dp
    val circleSize = 10.dp
    val innerCircle = 7.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
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
