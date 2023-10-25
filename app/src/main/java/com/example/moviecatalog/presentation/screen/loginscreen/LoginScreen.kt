package com.example.moviecatalog.presentation.screen.loginscreen

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
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.screen.common.AppBar
import com.example.moviecatalog.presentation.ui.theme.spanStyleAccent
import com.example.moviecatalog.presentation.ui.theme.spanStyleGray

@Composable
fun LoginScreen(router: AppRouter, viewModel: LoginViewModel) {
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
        AppBar {
            router.toAuth()
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
                    text = stringResource(R.string.login)
                )

                OutlinedTextField(
                    value = loginState.login,
                    onValueChange = { viewModel.processIntent(LoginIntent.UpdateLogin(it)) },
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
                    text = stringResource(R.string.password)

                )
                OutlinedTextField(
                    value = loginState.password,
                    onValueChange = {
                        viewModel.processIntent(LoginIntent.UpdatePassword(it))
                    },
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
                    append(stringResource(R.string.need_register) + " ")
                }

                withStyle(style = spanStyleAccent) {
                    append(stringResource(R.string.need_register_clickable))
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