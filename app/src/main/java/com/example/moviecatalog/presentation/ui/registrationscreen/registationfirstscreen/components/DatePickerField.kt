package com.example.moviecatalog.presentation.ui.registrationscreen.registationfirstscreen.components

import android.icu.util.Calendar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField() {
    var datePickerOpened by remember { mutableStateOf(false) }
    val currentDate = Calendar.getInstance().timeInMillis

    OutlinedTextField(
        value = "",
        onValueChange = { },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            IconButton(
                onClick = { datePickerOpened = true }
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            }
        },
    )

    if (datePickerOpened) {
        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = {
                datePickerOpened = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerOpened = false
                    }
                ) {
                    Text(
                        text = "OK"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        datePickerOpened = false
                    }
                ) {
                    Text(
                        text = "Отмена"
                    )
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}