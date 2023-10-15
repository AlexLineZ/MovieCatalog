package com.example.moviecatalog.presentation.ui.screen.registrationsecondscreen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.common.Descriptions
import com.example.moviecatalog.presentation.router.LoginRouter
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.spanStyleAccent
import com.example.moviecatalog.presentation.ui.theme.spanStyleGray

@Composable
fun RegistrationSecondScreen(router: LoginRouter) {
    val focusManager = LocalFocusManager.current
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .align(alignment = Alignment.Center)
            ) {
                Text(
                    text = Constants.LOGO,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    color = AccentColor
                )
            }
            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.CenterStart)
                    .align(alignment = Alignment.CenterStart)
            ) {
                IconButton(
                    onClick = { router.toRegistration },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            }
        }

        Text(
            text = Constants.REGISTRATION,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = Constants.PASSWORD,
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White

                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(IntrinsicSize.Min),
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                isPasswordVisible = !isPasswordVisible
                            }
                        ) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility
                                else Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Подтвердите пароль",
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White

                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(IntrinsicSize.Min),
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                isConfirmPasswordVisible = !isConfirmPasswordVisible
                            }
                        ) {
                            Icon(
                                imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility
                                else Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }

        Button(
            onClick = { router.toMain() },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = Constants.TO_REGISTER
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentSize(Alignment.BottomCenter)
                .padding(16.dp),
        ){
            val highlightedText = buildAnnotatedString {
                withStyle(style = spanStyleGray){
                    append(Descriptions.NEED_LOGIN)
                }

                withStyle(style = spanStyleAccent) {
                    append(Descriptions.NEED_LOGIN_CLICKABLE)
                }
            }

            ClickableText(
                onClick ={ offset ->
                    if (offset >= 16){
                        router.toLogin()
                    }
                },
                text = highlightedText
            )
        }
    }
}