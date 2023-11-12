package com.example.moviecatalog.presentation.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.presentation.ui.theme.ErrorAccentColor
import com.example.moviecatalog.presentation.ui.theme.RedColor
import com.example.moviecatalog.presentation.ui.theme.Values.BigRound
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: ((String) -> Unit)? = null,
    error: String? = null,
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W500)
            )

            OutlinedTextField(
                value = value,
                onValueChange = {
                    if (onValueChange != null) {
                        onValueChange(it)
                    }
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MiddlePadding),
                shape = RoundedCornerShape(BigRound),
                isError = error != null,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    errorBorderColor = RedColor,
                    errorContainerColor = RedColor.copy(alpha = 0.1f)
                ),
                textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W400)
            )

            error?.let {
                Text (
                    text = it,
                    modifier = Modifier
                        .padding(top = MiddlePadding),
                    color = ErrorAccentColor,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400)
                )
            }
        }
    }
}
