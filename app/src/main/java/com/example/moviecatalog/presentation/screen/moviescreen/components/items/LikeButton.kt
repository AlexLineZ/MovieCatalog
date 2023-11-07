package com.example.moviecatalog.presentation.screen.moviescreen.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.ChipColor
import com.example.moviecatalog.presentation.ui.theme.WhiteColor

@Composable
fun LikeButton(
    isLiked: Boolean,
    onClickToLikeButton: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = ChipColor,
                shape = CircleShape
            )
            .wrapContentSize()
    ) {
        IconButton(
            onClick = { onClickToLikeButton() },
            modifier = Modifier
                .size(40.dp),
            content = {
                Icon(
                    imageVector = if (isLiked)
                        ImageVector.vectorResource(id = R.drawable.like_focused)
                    else
                        ImageVector.vectorResource(id = R.drawable.like_unfocused),
                    contentDescription = null,
                    tint = if (isLiked) AccentColor else WhiteColor,
                )
            }
        )
    }
}
