package com.example.moviecatalog.presentation.screen.favouritescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.domain.model.movie.MovieElement


@Composable
fun MovieCardFavourite(movie: MovieElement, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = movie.poster,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(205.dp)
                .clip(RoundedCornerShape(3.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = movie.name ?: Constants.EMPTY_STRING,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Start)
        )
    }
}