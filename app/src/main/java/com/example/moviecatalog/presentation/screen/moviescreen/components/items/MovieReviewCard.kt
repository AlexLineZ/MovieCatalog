package com.example.moviecatalog.presentation.screen.moviescreen.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.common.formatDateToNormal
import com.example.moviecatalog.domain.model.movie.Review
import com.example.moviecatalog.presentation.screen.common.MarkWithStar
import com.example.moviecatalog.presentation.ui.theme.GrayTextColor

@Composable
fun MovieReviewCard(review: Review){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = if (review.author?.avatar == null) R.drawable.anonymus
                        else review.author?.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = review.author?.nickName
                    ?: Constants.EMPTY_STRING,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )

            MarkWithStar(review.rating)
        }


        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = review.reviewText ?: Constants.EMPTY_STRING,
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Start
            )

            Text(
                text = formatDateToNormal(review.createDateTime),
                fontSize = 12.sp,
                color = GrayTextColor,
                textAlign = TextAlign.Start
            )
        }
    }
}