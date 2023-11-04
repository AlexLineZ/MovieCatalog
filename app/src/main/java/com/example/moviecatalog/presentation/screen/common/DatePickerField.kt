package com.example.moviecatalog.presentation.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviecatalog.R
import com.example.moviecatalog.domain.state.RegistrationState
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationIntent
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationViewModel
import com.example.moviecatalog.common.formatDate
import com.example.moviecatalog.common.formatDateToISO8601
import java.time.LocalDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    viewModel: RegistrationViewModel,
    state: RegistrationState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.date_of_birthday)
            )

            OutlinedTextField(
                value = state.date,
                readOnly = true,
                onValueChange = {
                    viewModel.processIntent(RegistrationIntent.UpdateBirthday(state.birthday, it))
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.processIntent(RegistrationIntent.UpdateDatePickerVisibility)
                        }
                    ) {
                        Icon (
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null
                        )
                    }
                },
            )

            if (viewModel.isDatePickerOpen()) {
                val datePickerState = rememberDatePickerState()

                DatePickerDialog(
                    onDismissRequest = {
                        viewModel.processIntent(RegistrationIntent.UpdateDatePickerVisibility)
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                val date = datePickerState.selectedDateMillis?.let { Date(it) }
                                viewModel.processIntent(RegistrationIntent.UpdateBirthday(
                                    formatDateToISO8601(date),
                                    formatDate(date)
                                ))
                                viewModel.processIntent(RegistrationIntent.UpdateDatePickerVisibility)
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.ok),
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                viewModel.processIntent(RegistrationIntent.UpdateDatePickerVisibility)
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.cancel),
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
    }
}