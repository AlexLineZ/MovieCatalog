package com.example.moviecatalog.presentation.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.BaseButtonColor
import com.example.moviecatalog.presentation.ui.theme.SecondButtonColor

@Composable
fun PairButtons(
    firstLabel: String,
    firstClick: () -> Unit,
    secondLabel: String,
    secondClick: () -> Unit,
    modifier: Modifier,
    firstEnabled: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { firstClick() },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                enabled = firstEnabled,
                colors = BaseButtonColor
            ) {
                Text(
                    text = firstLabel,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W600
                    )
                )
            }

            Button(
                onClick = { secondClick() },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SecondButtonColor,
                    contentColor = AccentColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = secondLabel,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W600
                    )
                )
            }
        }
    }
}