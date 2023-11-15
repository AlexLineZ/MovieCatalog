package com.example.moviecatalog.presentation.screen.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviecatalog.common.MarkSelector
import com.example.moviecatalog.common.MarkSelector.setColorForMark
import com.example.moviecatalog.domain.model.movie.MovieElement
import com.example.moviecatalog.presentation.screen.common.MarkWithStar
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor
import com.example.moviecatalog.presentation.ui.theme.ChipColor
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.CenterPadding
import com.example.moviecatalog.presentation.ui.theme.Values.LittlePadding
import com.example.moviecatalog.presentation.ui.theme.Values.LittleRound
import com.example.moviecatalog.presentation.ui.theme.Values.MicroPadding
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieCard(
    movie: MovieElement,
    onClick: () -> Unit,
    userMark: Int?,
    anotherMark: Float? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = BasePadding, end = BasePadding, top = 12.dp)
            .clickable(
                onClick = onClick
            )
    ) {

        Box {
            AsyncImage(
                model = movie.poster,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.Yellow),
                contentScale = ContentScale.FillBounds
            )

            if (movie.reviews != null) {
                val mark = MarkSelector.markCalculation(movie.reviews)
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .align(Alignment.TopStart)
                        .padding(MicroPadding)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(LittleRound))
                            .background(
                                if (anotherMark != null) setColorForMark(anotherMark)
                                else mark.color
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = anotherMark?.toString() ?: mark.mark,
                            style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.W700),
                            color = BackgroundColor,
                            modifier = Modifier.padding(
                                horizontal = MiddlePadding,
                                vertical = MicroPadding
                            )
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(start = BasePadding)
                .fillMaxWidth()
        ) {
            Row {
                movie.name?.let {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (userMark != null) {
                    MarkWithStar(
                        value = userMark,
                        modifier = Modifier
                    )
                }
            }

            Text(
                text = movie.year.toString() + " • " + movie.country,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W400),
                modifier = Modifier.padding(top = LittlePadding, bottom = CenterPadding)
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(LittlePadding),
                verticalArrangement = Arrangement.spacedBy(LittlePadding)
            ) {
                movie.genres?.forEach { genre ->
                    genre.name?.let { Chip(text = it) }
                }
            }
        }
    }
}