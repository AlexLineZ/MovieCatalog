package com.example.moviecatalog.presentation.screen.registrationscreen

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
import com.example.moviecatalog.presentation.screen.common.AdviceText
import com.example.moviecatalog.presentation.screen.common.AppBar
import com.example.moviecatalog.presentation.screen.common.LoadingItem
import com.example.moviecatalog.presentation.screen.common.PasswordTextField
import com.example.moviecatalog.presentation.ui.theme.ErrorAccentColor
import com.example.moviecatalog.presentation.ui.theme.BaseButtonColor
import com.example.moviecatalog.presentation.ui.theme.RedColor
import com.example.moviecatalog.presentation.ui.theme.spanStyleAccent
import com.example.moviecatalog.presentation.ui.theme.spanStyleGray

@OptIn(ExperimentalMaterial3Api::class)
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
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W700),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )

        PasswordTextField(
            label = stringResource(R.string.password),
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
            transformationState = state.isPasswordHide,
            onButtonClick = {viewModel.processIntent(RegistrationIntent.UpdatePasswordVisibility)},
            errorText = state.isErrorPasswordText,
            modifier = Modifier
        )


        PasswordTextField(
            label = stringResource(R.string.confirm_password),
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
            transformationState = state.isConfirmPasswordHide,
            onButtonClick = {
                viewModel.processIntent(RegistrationIntent.UpdateConfirmPasswordVisibility)
            },
            errorText = state.isErrorConfirmPasswordText,
            modifier = Modifier.padding(top = 15.dp)
        )

        Button(
            onClick = {
                viewModel.processIntent(RegistrationIntent.Registration(state) { router.toMain() })
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
                .height(IntrinsicSize.Min),
            enabled = viewModel.isRegisterButtonAvailable(),
            colors = BaseButtonColor
        ) {
            Text(
                text = stringResource(R.string.to_register)
            )
        }

        if (state.isLoading){
            Spacer(modifier = Modifier.fillMaxWidth().padding(top = 16.dp))
            LoadingItem()
        }

        AdviceText(
            baseText = stringResource(R.string.need_login),
            clickableText = stringResource(R.string.need_login_clickable),
            onClick = { router.toLogin() },
            modifier = Modifier.weight(1f)
        )
    }
}