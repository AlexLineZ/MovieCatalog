package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.moviecatalog.presentation.screen.profilescreen.ProfileIntent
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor
import com.example.moviecatalog.presentation.ui.theme.BaseButtonColor
import com.example.moviecatalog.presentation.ui.theme.Gray400Color
import com.example.moviecatalog.presentation.ui.theme.SecondButtonColor
import com.example.moviecatalog.presentation.ui.theme.YellowStarColor

@Composable
fun ReviewDialog(
    onRatingSelected: (Int) -> Unit,
    onReviewTextChanged: (String) -> Unit,
    onAnonymousCheckedChanged: (Boolean) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    var rating by remember { mutableStateOf(0) }
    var reviewText by remember { mutableStateOf("") }
    var isAnonymous by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = {  },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Absolute.spacedBy(10.dp)
            ) {
                Text(
                    text = "Оставить отзыв",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    ),
                )

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    repeat(10) { index ->
                        IconButton(
                            onClick = {
                                rating = index + 1
                                onRatingSelected(rating)
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = if (index < rating)
                                    ImageVector.vectorResource(R.drawable.raiting_star_focused)
                                else
                                    ImageVector.vectorResource(R.drawable.raiting_star_unfocused),
                                contentDescription = null,
                                tint = if (index < rating) YellowStarColor else Gray400Color
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = reviewText,
                    textStyle = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 15.sp
                    ),
                    onValueChange = {
                        reviewText = it
                    },
                    label = { Text("Напишите отзыв") },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 98.dp)
                )
                Row(
                    modifier = Modifier.clickable {
                        isAnonymous = !isAnonymous
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isAnonymous,
                        onCheckedChange = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Анонимный отзыв",
                        style = TextStyle(
                            fontWeight = FontWeight.W500,
                            fontSize = 15.sp,
                            color =  MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(
                            onClick = {  },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min)
                                .padding(top = 8.dp, bottom = 4.dp),
                            colors = BaseButtonColor
                        ) {
                            Text(
                                text = stringResource(R.string.save)
                            )
                        }

                        Button(
                            onClick = {  },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SecondButtonColor,
                                contentColor = AccentColor
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min)
                                .padding(top = 8.dp, bottom = 4.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.cancel)
                            )
                        }
                    }
                }
            }
        }
    }
}

