package com.example.moviecatalog.presentation.screen.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.presentation.screen.profilescreen.components.DatePickerFieldForProfile
import com.example.moviecatalog.presentation.screen.common.GenderSelectionButton
import com.example.moviecatalog.presentation.screen.common.OutlinedTextFieldWithLabel
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.SecondButtonColor

@Composable
fun ProfileScreen (viewModel: ProfileViewModel) {
    val focusManager = LocalFocusManager.current
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = state.avatarLink,
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = state.nickName ?: Constants.EMPTY_STRING,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            item{
                OutlinedTextFieldWithLabel(
                    label = stringResource(R.string.email),
                    value = state.email,
                    onValueChange = {
                        viewModel.processIntent(ProfileIntent.UpdateEmail(it))
                    },
                    error = state.emailError?.let { stringResource(it) }
                )

                OutlinedTextFieldWithLabel(
                    label = stringResource(R.string.avatar_link),
                    value = state.avatarLink ?: Constants.EMPTY_STRING,
                    onValueChange = { viewModel.processIntent(ProfileIntent.UpdateAvatarLink(it)) }
                )

                OutlinedTextFieldWithLabel(
                    label = stringResource(R.string.name),
                    value = state.name,
                    onValueChange = { viewModel.processIntent(ProfileIntent.UpdateName(it)) }
                )

                GenderSelectionButton(
                    updateGender = { viewModel.processIntent(ProfileIntent.UpdateGender(it)) },
                    state = state.gender
                )

                DatePickerFieldForProfile(
                    viewModel = viewModel,
                    state = state
                )

                Box(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min)
                                .padding(top = 8.dp, bottom = 8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.save)
                            )
                        }

                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SecondButtonColor,
                                contentColor = AccentColor
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min)
                                .padding(top = 8.dp, bottom = 8.dp)
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