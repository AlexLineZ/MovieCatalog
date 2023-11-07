package com.example.moviecatalog.presentation.screen.moviescreen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.data.model.Mark
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor

@Composable
fun LabelWithButtonAndMark(
    mark: Mark,
    movieName: String,
    isLiked: Boolean,
    onClickToLikeButton: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = mark.color,
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Text(
                text = mark.mark,
                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W500),
                color = BackgroundColor,
                modifier = Modifier.padding(start = 14.dp, top = 4.dp, end = 14.dp, bottom = 4.dp)
            )
        }

        Text(
            text = movieName,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        LikeButton(
            isLiked = isLiked,
            onClickToLikeButton = onClickToLikeButton
        )
    }
}