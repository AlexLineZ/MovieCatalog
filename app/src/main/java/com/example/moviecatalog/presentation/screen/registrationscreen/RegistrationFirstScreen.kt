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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import com.example.moviecatalog.presentation.router.LoginRouter
import com.example.moviecatalog.presentation.screen.common.AppBar
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationIntent
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationViewModel
import com.example.moviecatalog.presentation.screen.common.DatePickerField
import com.example.moviecatalog.presentation.screen.common.GenderSelectionButton
import com.example.moviecatalog.presentation.ui.theme.spanStyleAccent
import com.example.moviecatalog.presentation.ui.theme.spanStyleGray

@Composable
fun RegistrationFirstScreen(
    router: LoginRouter,
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
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
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
                    text = stringResource(R.string.name)
                )

                OutlinedTextField(
                    value = registrationState.name,
                    onValueChange = { viewModel.processIntent(RegistrationIntent.UpdateName(it)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
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
                    text = stringResource(R.string.gender)
                )
                GenderSelectionButton(viewModel, registrationState)
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
                    text = stringResource(R.string.login)
                )

                OutlinedTextField(
                    value = registrationState.login,
                    onValueChange = { viewModel.processIntent(RegistrationIntent.UpdateLogin(it)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
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
                    text = stringResource(R.string.email)
                )

                OutlinedTextField(
                    value = registrationState.email,
                    onValueChange = { viewModel.processIntent(RegistrationIntent.UpdateEmail(it)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
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
                    text = stringResource(R.string.date_of_birthday)
                )

                DatePickerField(viewModel, registrationState)
            }
        }

        Button(
            onClick = { router.toPasswordRegistration() },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
                .height(IntrinsicSize.Min),
            enabled = viewModel.isContinueButtonAvailable()
        ) {
            Text(
                text = stringResource(R.string.continue_)
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