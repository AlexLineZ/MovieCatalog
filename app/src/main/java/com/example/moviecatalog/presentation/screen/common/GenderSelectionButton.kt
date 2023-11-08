package com.example.moviecatalog.presentation.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.SecondButtonColor
import com.example.moviecatalog.presentation.ui.theme.SuperDarkGrayColor
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding
import com.example.moviecatalog.presentation.ui.theme.Values.MiddleRound

@Composable
fun GenderSelectionButton(
    updateGender: (Int) -> Unit,
    state: Int,
    modifier: Modifier
) {
    val man = stringResource(R.string.man)
    val woman = stringResource(R.string.woman)
    
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.gender),
                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W500)
            )

            Row(
                modifier = Modifier
                    .padding(top = MiddlePadding)
                    .background(
                        color = SecondButtonColor,
                        shape = RoundedCornerShape(MiddleRound)
                    )
            ) {
                Button(
                    onClick = {
                        updateGender(0)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(IntrinsicSize.Min)
                        .padding(start = 2.dp, top = 2.dp, bottom = 2.dp, end = 0.dp),
                    shape = RoundedCornerShape(7.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (state == 0)
                            MaterialTheme.colorScheme.tertiary else SecondButtonColor,
                        contentColor = SuperDarkGrayColor
                    )
                ) {
                    Text(man)
                }

                Button(
                    onClick = {
                        updateGender(1)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(IntrinsicSize.Min)
                        .padding(start = 0.dp, top = 2.dp, bottom = 2.dp, end = 2.dp),
                    shape = RoundedCornerShape(7.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (state == 1)
                            MaterialTheme.colorScheme.tertiary else SecondButtonColor,
                        contentColor = SuperDarkGrayColor
                    )
                ) {
                    Text(woman)
                }
            }
        }
    }
}