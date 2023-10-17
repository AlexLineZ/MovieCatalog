package com.example.moviecatalog.presentation.ui.loginscreen

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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Descriptions
import com.example.moviecatalog.presentation.router.LoginRouter
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.spanStyleAccent
import com.example.moviecatalog.presentation.ui.theme.spanStyleGray

@Composable
fun LoginScreen(router: LoginRouter, viewModel: LoginViewModel) {
    val loginState by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

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
                    text = stringResource(R.string.logo),
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
                    onClick = { router.toAuth() },
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
            }
        }

        Text(
            text = stringResource(R.string.login_to),
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
                    text = stringResource(R.string.login),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )

                OutlinedTextField(
                    value = loginState.login,
                    onValueChange = { viewModel.updateLogin(it) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(IntrinsicSize.Min),
                    shape = RoundedCornerShape(10.dp),
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
                    text = stringResource(R.string.password),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White

                )
                OutlinedTextField(
                    value = loginState.password,
                    onValueChange = { viewModel.updatePassword(it) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(IntrinsicSize.Min),
                    shape = RoundedCornerShape(10.dp),
                    visualTransformation = if (loginState.isPasswordHide)
                        VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                viewModel.processIntent(LoginIntent.UpdatePasswordVisibility)
                            }
                        ) {
                            Icon(
                                imageVector = if (loginState.isPasswordHide) Icons.Default.Visibility
                                else Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }

        Button(
            onClick = {
                viewModel.processIntent(LoginIntent.Login(loginState))
                router.toMain()
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = stringResource(R.string.login_button)
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
                    append(Descriptions.NEED_REGISTER)
                }

                withStyle(style = spanStyleAccent) {
                    append(Descriptions.NEED_REGISTER_CLICKABLE)
                }
            }

            ClickableText(
                onClick = { offset ->
                    if (offset >= 16){
                        router.toRegistration()
                    }
                },
                text = highlightedText
            )
        }
    }
}