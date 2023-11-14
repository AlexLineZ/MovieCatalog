package com.example.moviecatalog.presentation.screen.selectauthscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.router.AppRouter
import com.example.moviecatalog.presentation.screen.common.PairButtons
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding
import com.example.moviecatalog.presentation.ui.theme.Values.MoreSpaceBetweenObjects

@Composable
fun SelectAuthScreen(router: AppRouter) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BasePadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.amico),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp)
        )

        Box(
            modifier = Modifier
                .padding(vertical = 35.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.auth_description_first),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W700),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(R.string.auth_description_second),
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W400),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = MiddlePadding)
                )
            }
        }

        PairButtons(
            firstLabel = stringResource(R.string.registration),
            firstClick = { router.toRegistration() },
            secondLabel = stringResource(R.string.login_button),
            secondClick = { router.toLogin() },
            modifier = Modifier.padding(top = MoreSpaceBetweenObjects, bottom = BasePadding)
        )
    }
}