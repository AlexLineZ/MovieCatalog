package com.example.moviecatalog.presentation.screen.selectauthscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.router.LoginRouter
import com.example.moviecatalog.presentation.screen.common.AppBar
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.SecondButtonColor

@Composable
fun SelectAuthScreen(router: LoginRouter) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = AccentColor
                )
            }

            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.CenterStart)
                    .align(alignment = Alignment.CenterStart)
            ) {
                IconButton(
                    onClick = {  },
                    enabled = false
                ){}
            }
        }
        Image(
            painter = painterResource(id = R.drawable.amico),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(CircleShape)
        )

        Box(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.auth_description_first),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = stringResource(R.string.auth_description_second),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = { router.toRegistration() },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.registration)
                    )
                }

                Button(
                    onClick = { router.toLogin() },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SecondButtonColor,
                        contentColor = AccentColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.login_button)
                    )
                }
            }
        }
    }
}