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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.domain.model.review.Review
import com.example.moviecatalog.domain.state.MovieState
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.MovieReviewCard
import com.example.moviecatalog.presentation.screen.moviescreen.components.items.MovieReviewCurrentUserCard
import com.example.moviecatalog.presentation.ui.theme.AccentColor

@Composable
fun MovieReviewsSection(
    list: ArrayList<Review>?,
    state: MovieState,
    onClickDialog: () -> Unit,
    onSaveClick: () -> Unit,
    onRatingSelected: (Int) -> Unit,
    onAnonymousCheckedChanged: (Boolean) -> Unit,
    onReviewTextChanged: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onDropClick: () -> Unit,
    isButtonAvailable: Boolean
) {

    if (state.isReviewDialogOpen) {
        ReviewDialog(
            state = state,
            onRatingSelected = { onRatingSelected(it)},
            onReviewTextChanged = {onReviewTextChanged(it) },
            onAnonymousCheckedChanged = { onAnonymousCheckedChanged(it) },
            onSaveClick = { onSaveClick() },
            onCancelClick = { onClickDialog() },
            isButtonAvailable = isButtonAvailable
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.reviews),
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                color = Color.White
            )

            if (state.userReview == null) {
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
                            onClickDialog()
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

        if (state.userReview != null){
            MovieReviewCurrentUserCard(
                state = state,
                review = state.userReview!!,
                onSaveClick = { onSaveClick() },
                onDeleteClick = { onDeleteClick() },
                onDropClick = { onDropClick() },
                onRatingSelected = { onRatingSelected(it) },
                onAnonymousCheckedChanged = { onAnonymousCheckedChanged(it) },
                onReviewTextChanged = { onReviewTextChanged(it) },
                onClickDialog = { onClickDialog() },
                isButtonAvailable = isButtonAvailable
            )
        }

        list?.forEach { review ->
            if (review != state.userReview &&
                review.author?.userId != state.userId
            ){
                MovieReviewCard(review)
            }
        }
    }
}