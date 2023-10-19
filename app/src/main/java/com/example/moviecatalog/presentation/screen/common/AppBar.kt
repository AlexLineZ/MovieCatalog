package com.example.moviecatalog.presentation.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.AccentColor

@Composable
fun AppBar(
    toAnotherScreen: () -> Unit
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
                onClick = { toAnotherScreen() },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        }
    }
}