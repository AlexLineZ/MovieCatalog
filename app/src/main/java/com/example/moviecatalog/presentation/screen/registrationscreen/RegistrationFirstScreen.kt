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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.domain.validator.EmailValidator
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.screen.common.AdviceText
import com.example.moviecatalog.presentation.screen.common.AppBar
import com.example.moviecatalog.presentation.screen.common.DatePickerField
import com.example.moviecatalog.presentation.screen.common.GenderSelectionButton
import com.example.moviecatalog.presentation.screen.common.OutlinedTextFieldWithLabel
import com.example.moviecatalog.presentation.ui.theme.BaseButtonColor
import com.example.moviecatalog.presentation.ui.theme.spanStyleAccent
import com.example.moviecatalog.presentation.ui.theme.spanStyleGray

@Composable
fun RegistrationFirstScreen(
    router: AppRouter,
    viewModel: RegistrationViewModel
) {
    val focusManager = LocalFocusManager.current
    val registrationState by viewModel.state.collectAsState()

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

        AppBar{
            router.toAuth()
        }

        Text(
            text = stringResource(R.string.registration),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W700
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )
        
        LazyColumn {
            item {
                OutlinedTextFieldWithLabel(
                    label = stringResource(R.string.name),
                    value = registrationState.name,
                    onValueChange = { viewModel.processIntent(RegistrationIntent.UpdateName(it)) },
                    error = null,
                    modifier = Modifier
                )

                GenderSelectionButton(
                    updateGender = { viewModel.processIntent(RegistrationIntent.UpdateGender(it)) },
                    state = registrationState.gender,
                    modifier = Modifier.padding(top = 15.dp)
                )

                OutlinedTextFieldWithLabel(
                    label = stringResource(R.string.login),
                    value = registrationState.login,
                    onValueChange = { viewModel.processIntent(RegistrationIntent.UpdateLogin(it)) },
                    error = null,
                    modifier = Modifier.padding(top = 15.dp)
                )

                OutlinedTextFieldWithLabel(
                    label = stringResource(R.string.email),
                    value = registrationState.email,
                    onValueChange = {
                        viewModel.processIntent(RegistrationIntent.UpdateEmail(it))
                        viewModel.processIntent(
                            RegistrationIntent.UpdateErrorText(
                                EmailValidator(),
                                it
                            )
                        )

                    },
                    error = registrationState.isErrorEmailText,
                    modifier = Modifier.padding(top = 15.dp)
                )

                DatePickerField(
                    viewModel = viewModel,
                    state = registrationState
                )

                Button(
                    onClick = { router.toPasswordRegistration() },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(IntrinsicSize.Min),
                    enabled = viewModel.isContinueButtonAvailable(),
                    colors = BaseButtonColor
                ) {
                    Text(
                        text = stringResource(R.string.continue_),
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W600)
                    )
                }
            }
        }

        AdviceText(
            baseText = stringResource(R.string.need_login),
            clickableText = stringResource(R.string.need_login_clickable),
            onClick = { router.toLogin() },
            modifier = Modifier.weight(1f)
        )
    }
}