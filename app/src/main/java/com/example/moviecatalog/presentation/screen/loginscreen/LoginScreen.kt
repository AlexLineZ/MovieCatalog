package com.example.moviecatalog.presentation.screen.loginscreen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import com.example.moviecatalog.presentation.screen.common.AdviceText
import com.example.moviecatalog.presentation.screen.common.AppBar
import com.example.moviecatalog.presentation.screen.common.LoadingItem
import com.example.moviecatalog.presentation.screen.common.OutlinedTextFieldWithLabel
import com.example.moviecatalog.presentation.screen.common.PasswordTextField
import com.example.moviecatalog.presentation.ui.theme.BaseButtonColor
import com.example.moviecatalog.presentation.ui.theme.RedColor
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
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W700),
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )

        OutlinedTextFieldWithLabel(
            label = stringResource(R.string.login),
            value = loginState.login,
            onValueChange = { viewModel.processIntent(LoginIntent.UpdateLogin(it)) },
            error = loginState.isErrorText,
            modifier = Modifier
        )

        PasswordTextField(
            label = stringResource(R.string.password),
            value = loginState.password,
            onValueChange = { viewModel.processIntent(LoginIntent.UpdatePassword(it)) },
            transformationState = loginState.isPasswordHide,
            onButtonClick = { viewModel.processIntent(LoginIntent.UpdatePasswordVisibility) },
            errorText = loginState.isErrorText,
            modifier = Modifier.padding(top = 15.dp)
        )


        Button(
            onClick = {
                viewModel.processIntent(LoginIntent.Login(loginState) { router.toMain() })
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(top = 20.dp),
            enabled = !loginState.isLoading && viewModel.isLoginButtonAvailable(),
            colors = BaseButtonColor
        ) {
            Text(
                text = stringResource(R.string.login_button),
                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W600)
            )
        }

        if (loginState.isLoading){
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp))
            LoadingItem()
        }

        AdviceText(
            baseText = stringResource(R.string.need_register),
            clickableText = stringResource(R.string.need_register_clickable),
            onClick = { router.toRegistration() },
            modifier = Modifier.weight(1f)
        )
    }
}