package com.example.moviecatalog.presentation.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.spanStyleAccent
import com.example.moviecatalog.presentation.ui.theme.spanStyleGray

@Composable
fun AdviceText(
    baseText: String,
    clickableText: String,
    onClick: () -> Unit,
    modifier: Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.BottomCenter)
            .padding(16.dp),
    ){
        val highlightedText = buildAnnotatedString {
            withStyle(style = spanStyleGray){
                append("$baseText ")
            }

            withStyle(style = spanStyleAccent) {
                append(clickableText)
            }
        }

        ClickableText(
            onClick = { offset ->
                if (offset >= 16){
                    onClick()
                }
            },
            text = highlightedText
        )
    }
}