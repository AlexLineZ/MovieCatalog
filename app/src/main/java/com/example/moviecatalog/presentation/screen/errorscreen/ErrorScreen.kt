package com.example.moviecatalog.presentation.screen.errorscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.BaseButtonColor
import com.example.moviecatalog.presentation.ui.theme.Values

@Composable
fun ErrorScreen(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Values.BasePadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.network_error),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp),
            tint = AccentColor
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
                    text = stringResource(id = R.string.error_connection),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W700),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(id = R.string.error_description),
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W400),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = Values.MiddlePadding)
                )
            }
        }

        Button(
            onClick = {
                onClick()
            },
            shape = RoundedCornerShape(Values.BigRound),
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(top = Values.MoreSpaceBetweenObjects),
            colors = BaseButtonColor
        ) {
            Text(
                text = stringResource(id = R.string.to_main),
                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W600)
            )
        }
    }
}