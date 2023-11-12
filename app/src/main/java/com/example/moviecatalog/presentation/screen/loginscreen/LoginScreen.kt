package com.example.moviecatalog.presentation.screen.loginscreen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.screen.common.AdviceText
import com.example.moviecatalog.presentation.screen.common.AppBar
import com.example.moviecatalog.presentation.screen.common.loading.LoadingItem
import com.example.moviecatalog.presentation.screen.common.OutlinedTextFieldWithLabel
import com.example.moviecatalog.presentation.screen.common.PasswordTextField
import com.example.moviecatalog.presentation.ui.theme.BaseButtonColor
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.BigRound
import com.example.moviecatalog.presentation.ui.theme.Values.MoreSpaceBetweenObjects
import com.example.moviecatalog.presentation.ui.theme.Values.SpaceBetweenObjects

@Composable
fun LoginScreen(router: AppRouter, viewModel: LoginViewModel) {
    val loginState by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BasePadding)
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
            modifier = Modifier.padding(top = MoreSpaceBetweenObjects, bottom = SpaceBetweenObjects)
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
            modifier = Modifier.padding(top = SpaceBetweenObjects)
        )


        Button(
            onClick = {
                viewModel.processIntent(LoginIntent.Login(loginState) { router.toMain() })
            },
            shape = RoundedCornerShape(BigRound),
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(top = MoreSpaceBetweenObjects),
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
                .padding(top = BasePadding))
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