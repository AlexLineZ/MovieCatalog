package com.example.moviecatalog.presentation.screen.mainscreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.Values.CenterPadding
import com.example.moviecatalog.presentation.ui.theme.Values.MiddlePadding
import com.example.moviecatalog.presentation.ui.theme.Values.pageCount

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicator(
    state: PagerState,
    modifier: Modifier
    ) {
    Box(
        modifier = modifier
            .padding(bottom = CenterPadding)
            .clip(RoundedCornerShape(28.dp))
            .background(color = Color.Black.copy(alpha = 0.25f))
    ) {
        Row(
            modifier = Modifier
                .padding(MiddlePadding),
            horizontalArrangement = Arrangement.spacedBy(MiddlePadding),
        ) {
            repeat(pageCount) { index ->
                Box(
                    modifier = Modifier
                        .size(MiddlePadding)
                ){
                    if(state.currentPage == index) {
                        Icon(
                            painterResource(R.drawable.dot_focused),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            painterResource(R.drawable.dot),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}