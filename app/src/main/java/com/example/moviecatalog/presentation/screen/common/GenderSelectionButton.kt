package com.example.moviecatalog.presentation.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviecatalog.R
import com.example.moviecatalog.domain.state.RegistrationState
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationIntent
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationViewModel
import com.example.moviecatalog.presentation.ui.theme.SecondButtonColor
import com.example.moviecatalog.presentation.ui.theme.SuperDarkGrayColor

@Composable
fun GenderSelectionButton(
    viewModel: RegistrationViewModel,
    state: RegistrationState
) {
    val man = stringResource(R.string.man)
    val woman = stringResource(R.string.woman)

    Row(
        modifier = Modifier
            .padding(top = 4.dp)
            .background(
                color = SecondButtonColor,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Button(
            onClick = {
                viewModel.processIntent(RegistrationIntent.UpdateGender(0))
            },
            modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Min)
                .padding(2.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (state.gender == 0)
                    MaterialTheme.colorScheme.tertiary else SecondButtonColor,
                contentColor = SuperDarkGrayColor
            )
        ) {
            Text(man)
        }

        Button(
            onClick = {
                viewModel.processIntent(RegistrationIntent.UpdateGender(1))
            },
            modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Min)
                .padding(2.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (state.gender == 1)
                    MaterialTheme.colorScheme.tertiary else SecondButtonColor,
                contentColor = SuperDarkGrayColor
            )
        ) {
            Text(woman)
        }
    }
}