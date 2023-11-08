package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor
import org.w3c.dom.Text

@Composable
fun MovieDescriptionSection(
    description: String,
    state: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp)
    ) {
        Text(
            text = description,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400),
            maxLines = if (state) Int.MAX_VALUE else 4,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            modifier = Modifier
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, BackgroundColor),
                        startY = size.height * 0.5f,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        if (!state) {
                            drawRect(gradient)
                        }
                    }
                }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Text(
                text = if (state) stringResource(id = R.string.less)
                    else stringResource(id = R.string.more),
                fontSize = 15.sp,
                color = AccentColor,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 16.dp)
            )

            Icon(
                imageVector = if (state) ImageVector.vectorResource(R.drawable.arrow_up)
                else ImageVector.vectorResource(R.drawable.arrow_down),
                contentDescription = null,
                tint = AccentColor
            )
        }
    }
}