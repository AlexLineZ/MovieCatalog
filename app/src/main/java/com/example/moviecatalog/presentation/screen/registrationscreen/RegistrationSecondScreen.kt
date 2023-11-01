package com.example.moviecatalog.presentation.screen.registrationscreen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.domain.validator.ConfirmPasswordValidator
import com.example.moviecatalog.domain.validator.PasswordValidator
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.screen.common.AppBar
import com.example.moviecatalog.presentation.ui.theme.ErrorAccentColor
import com.example.moviecatalog.presentation.ui.theme.baseButtonColor
import com.example.moviecatalog.presentation.ui.theme.spanStyleAccent
import com.example.moviecatalog.presentation.ui.theme.spanStyleGray

@Composable
fun RegistrationSecondScreen (
    router: AppRouter,
    viewModel: RegistrationViewModel
) {
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

        AppBar {
            router.toRegistration()
        }

        Text(
            text = stringResource(R.string.registration),
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
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.password),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White

                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = {
                        viewModel.processIntent(RegistrationIntent.UpdatePassword(it))
                        viewModel.processIntent(
                            RegistrationIntent.UpdateErrorText(
                                PasswordValidator(),
                                it
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(IntrinsicSize.Min),
                    shape = RoundedCornerShape(10.dp),
                    visualTransformation = if (state.isPasswordHide)
                        VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                viewModel.processIntent(RegistrationIntent.UpdatePasswordVisibility)
                            }
                        ) {
                            Icon(
                                imageVector =
                                    if (state.isPasswordHide)
                                        Icons.Default.Visibility
                                    else
                                        Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                )
                state.isErrorPasswordText?.let {
                    Text (
                        text = it,
                        modifier = Modifier
                            .padding(top = 4.dp),
                        color = ErrorAccentColor,
                        fontSize = 14.sp
                    )
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.confirm_password),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White

                )
                OutlinedTextField(
                    value = state.confirmPassword,
                    onValueChange = {
                        viewModel.processIntent(RegistrationIntent.UpdateConfirmPassword(it))
                        viewModel.processIntent(
                            RegistrationIntent.UpdateErrorText(
                                ConfirmPasswordValidator(),
                                state.password,
                                it
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(IntrinsicSize.Min),
                    shape = RoundedCornerShape(10.dp),
                    visualTransformation = if (state.isConfirmPasswordHide)
                        VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                viewModel.processIntent(
                                    RegistrationIntent.UpdateConfirmPasswordVisibility
                                )
                            }
                        ) {
                            Icon(
                                imageVector =
                                    if (state.isConfirmPasswordHide)
                                        Icons.Default.Visibility
                                    else
                                        Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                )
                state.isErrorConfirmPasswordText?.let {
                    Text (
                        text = it,
                        modifier = Modifier
                            .padding(top = 4.dp),
                        color = ErrorAccentColor,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Button(
            onClick = {
                viewModel.processIntent(RegistrationIntent.Registration(state))
                router.toMain()
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
                .height(IntrinsicSize.Min),
            enabled = viewModel.isRegisterButtonAvailable(),
            colors = baseButtonColor
        ) {
            Text(
                text = stringResource(R.string.to_register)
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
                    append(stringResource(R.string.need_login) + " ")
                }

                withStyle(style = spanStyleAccent) {
                    append(stringResource(R.string.need_login_clickable))
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