package com.example.moviecatalog.presentation.ui.registrationscreen.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.SecondButtonColor
import com.example.moviecatalog.presentation.ui.theme.SuperDarkGrayColor

@Composable
fun GenderSelectionButton() {
    val man = stringResource(R.string.man)
    val woman = stringResource(R.string.woman)

    var selectedGender by remember { mutableStateOf(man) }

    Row(
        modifier = Modifier
            .padding(top = 4.dp)
    ) {
        Button(
            onClick = {
                selectedGender = man
            },
            modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Min),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedGender == man)
                    MaterialTheme.colorScheme.tertiary else SecondButtonColor,
                contentColor = SuperDarkGrayColor
            )
        ) {
            Text(man)
        }

        Button(
            onClick = {
                selectedGender = woman
            },
            modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Min),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedGender == woman)
                    MaterialTheme.colorScheme.tertiary else SecondButtonColor,
                contentColor = SuperDarkGrayColor
            )
        ) {
            Text(woman)
        }
    }
}