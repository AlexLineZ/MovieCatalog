package com.example.moviecatalog.presentation.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.presentation.ui.theme.ErrorAccentColor

@Composable
fun OutlinedTextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: ((String) -> Unit)? = null,
    error: String? = null
) {
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
                text = label
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
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(10.dp),
                isError = error != null
            )

            error?.let {
                Text (
                    text = it,
                    modifier = Modifier
                        .padding(top = 4.dp),
                    color = ErrorAccentColor,
                    fontSize = 14.sp
                )
            }
        }
    }
}
