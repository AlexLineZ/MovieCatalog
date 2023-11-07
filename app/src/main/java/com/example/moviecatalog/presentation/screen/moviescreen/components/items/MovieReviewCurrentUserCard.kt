package com.example.moviecatalog.presentation.screen.moviescreen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.moviecatalog.domain.model.review.Review
import com.example.moviecatalog.domain.state.MovieState
import com.example.moviecatalog.presentation.screen.common.MarkWithStar
import com.example.moviecatalog.presentation.screen.moviescreen.components.ReviewDialog
import com.example.moviecatalog.presentation.screen.moviescreen.components.ReviewDropDownMenu
import com.example.moviecatalog.presentation.ui.theme.ChipColor
import com.example.moviecatalog.presentation.ui.theme.Gray400Color

@Composable
fun MovieReviewCurrentUserCard(
    state: MovieState,
    review: Review,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDropClick: () -> Unit,
    onRatingSelected: (Int) -> Unit,
    onAnonymousCheckedChanged: (Boolean) -> Unit,
    onReviewTextChanged: (String) -> Unit,
    onClickDialog: () -> Unit
){

    if (state.isReviewDialogOpen) {
        ReviewDialog(
            state = state,
            onRatingSelected = { onRatingSelected(it)},
            onReviewTextChanged = {onReviewTextChanged(it) },
            onAnonymousCheckedChanged = { onAnonymousCheckedChanged(it) },
            onSaveClick = { onSaveClick() },
            onCancelClick = { onClickDialog() }
        )
    }

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

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = review.author?.nickName
                        ?: Constants.EMPTY_STRING,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "мой отзыв",
                    fontSize = 12.sp,
                    color = Gray400Color,
                    textAlign = TextAlign.Start
                )
            }

            Row(){
                MarkWithStar(review.rating)

                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = ChipColor,
                            shape = CircleShape
                        )
                        .wrapContentSize()
                ) {
                    IconButton(
                        onClick = {
                            onDropClick()
                        },
                        modifier = Modifier.size(30.dp),
                        content = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.dots),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    )
                }
            }

            if (state.isDropDownMenuOpen) {
                ReviewDropDownMenu(
                    onEditClick = {
                        onClickDialog()
                    },
                    onDeleteClick = {
                        onDeleteClick()
                    },
                    onDropClick = {
                        onDropClick()
                    },
                    expanded = state.isDropDownMenuOpen
                )
            }
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
                color = Gray400Color,
                textAlign = TextAlign.Start
            )
        }
    }
}