package com.example.moviecatalog.presentation.screen.moviescreen.components.items

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.common.formatDateToNormal
import com.example.moviecatalog.domain.model.review.Review
import com.example.moviecatalog.presentation.screen.common.MarkWithStar
import com.example.moviecatalog.presentation.ui.theme.Gray400Color
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding

@Composable
fun MovieReviewCard(review: Review){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = BasePadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = if (review.author?.avatar == null || review.isAnonymous) R.drawable.anonymus
                        else review.author?.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = if (review.author?.nickName == null || review.isAnonymous)
                    stringResource(id = R.string.anonymous) else review.author?.nickName!!,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )

            MarkWithStar(review.rating)
        }


        Column(
            modifier = Modifier.padding(top = MiddlePadding)
        ) {
            Text(
                text = review.reviewText ?: Constants.EMPTY_STRING,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400),
                color = Color.White,
                textAlign = TextAlign.Start
            )

            Text(
                text = formatDateToNormal(review.createDateTime),
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W500),
                color = Gray400Color,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}