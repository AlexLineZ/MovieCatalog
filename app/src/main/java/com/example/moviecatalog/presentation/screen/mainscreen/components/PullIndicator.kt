package com.example.moviecatalog.presentation.screen.mainscreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import eu.bambooapps.material3.pullrefresh.PullRefreshState
import eu.bambooapps.material3.pullrefresh.pullRefreshIndicatorTransform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullIndicator(
    pullState: PullRefreshState,
    rotation: Float,
    refreshing: Boolean,
    modifier: Modifier
){
    Surface(
        modifier = modifier
            .size(50.dp)
            .pullRefreshIndicatorTransform(pullState)
            .rotate(rotation),
        shape = RoundedCornerShape(15.dp),
        color = AccentColor,
    ) {
        Box {
            if (refreshing) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(30.dp),
                    color = Color.White,
                    strokeWidth = 4.dp
                )
            }
        }
    }
}