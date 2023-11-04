package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.domain.model.review.Review
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.MovieReviewCard
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.MovieReviewCurrentUserCard
import com.example.moviecatalog.presentation.ui.theme.AccentColor

@Composable
fun MovieReviewsSection(
    list: ArrayList<Review>?,
    isDialogOpen: Boolean,
    userReview: Review?,
    onClick: () -> Unit
) {

    if (isDialogOpen) {
        ReviewDialog(
            onRatingSelected = { },
            onReviewTextChanged = { },
            onAnonymousCheckedChanged = {  },
            onSaveClick = {  },
            onCancelClick = { onClick() }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Отзывы",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            if (userReview == null) {
                Box(
                    modifier = Modifier
                        .background(
                            color = AccentColor,
                            shape = CircleShape
                        )
                        .wrapContentSize()
                ) {
                    IconButton(
                        onClick = {
                            onClick()
                        },
                        modifier = Modifier
                            .size(34.dp),
                        content = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.plus),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    )
                }
            }
        }

        if (userReview != null){
            MovieReviewCurrentUserCard(review = userReview)
        }

        list?.forEach { review ->
            if (review != userReview){
                MovieReviewCard(review)
            }
        }
    }
}