package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.moviecatalog.R
import com.example.moviecatalog.domain.state.MovieState
import com.example.moviecatalog.presentation.screen.common.PairButtons
import com.example.moviecatalog.presentation.ui.theme.Gray400Color
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.CenterPadding
import com.example.moviecatalog.presentation.ui.theme.Values.LittleRound
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding
import com.example.moviecatalog.presentation.ui.theme.YellowStarColor

@Composable
fun ReviewDialog(
    state: MovieState,
    onRatingSelected: (Int) -> Unit,
    onReviewTextChanged: (String) -> Unit,
    onAnonymousCheckedChanged: (Boolean) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    isButtonAvailable: Boolean,
    isCheckBoxAvailable: Boolean
) {
    Dialog(
        onDismissRequest = {
            onCancelClick()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BasePadding),
            shape = RoundedCornerShape(LittleRound),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(CenterPadding),
                verticalArrangement = Arrangement.Absolute.spacedBy(CenterPadding)
            ) {
                Text(
                    text = stringResource(id = R.string.leave_review),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    ),
                )

                Spacer(modifier = Modifier.height(LittleRound))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    repeat(10) { index ->
                        IconButton(
                            onClick = {
                                onRatingSelected(index + 1)
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = if (index < state.movieRating)
                                    ImageVector.vectorResource(R.drawable.raiting_star_focused)
                                else
                                    ImageVector.vectorResource(R.drawable.raiting_star_unfocused),
                                contentDescription = null,
                                tint = if (index < state.movieRating) YellowStarColor
                                    else Gray400Color
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = state.reviewText,
                    textStyle = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 15.sp
                    ),
                    onValueChange = {
                        onReviewTextChanged(it)
                    },
                    label = { Text(stringResource(id = R.string.write_review)) },
                    shape = RoundedCornerShape(LittleRound),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 98.dp)
                )
                Row(
                    modifier = if (isCheckBoxAvailable) {
                        Modifier.clickable {
                            onAnonymousCheckedChanged(!state.isAnonymous)
                        }
                    } else {
                        Modifier
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = state.isAnonymous,
                        onCheckedChange = null,
                        enabled = isCheckBoxAvailable
                    )

                    Spacer(modifier = Modifier.width(MiddlePadding))

                    Text(
                        text = stringResource(id = R.string.anon_review),
                        style = TextStyle(
                            fontWeight = FontWeight.W500,
                            fontSize = 15.sp,
                            color =  MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                PairButtons(
                    firstLabel = stringResource(R.string.save),
                    firstClick = { onSaveClick() },
                    secondLabel = stringResource(R.string.cancel),
                    secondClick = { onCancelClick() },
                    modifier = Modifier.padding(top = 25.dp),
                    firstEnabled = isButtonAvailable
                )
            }
        }
    }
}

