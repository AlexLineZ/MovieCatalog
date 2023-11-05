package com.example.moviecatalog.presentation.screen.moviescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.ChipColor
import com.example.moviecatalog.presentation.ui.theme.MenuGrayColor
import com.example.moviecatalog.presentation.ui.theme.RedColor
import com.example.moviecatalog.presentation.ui.theme.WhiteColor

@Composable
fun ReviewDropDownMenu(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    expanded: Boolean
) {
    Box(
        contentAlignment = Alignment.TopStart
    ){
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDeleteClick() },
            modifier = Modifier.background(
                color = ChipColor,
                shape = RoundedCornerShape(10.dp)
            )
                .width(IntrinsicSize.Min)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Редактировать",
                        color = WhiteColor,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                trailingIcon =
                {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.pencil),
                        contentDescription = null,
                        tint = WhiteColor,
                        modifier = Modifier.size(24.dp)
                    )
                },
                onClick = {
                    onEditClick()
                }
            )

            HorizontalDivider()

            DropdownMenuItem(
                text = {
                    Text(
                        text = "Удалить",
                        color = RedColor,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                trailingIcon =
                {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.basket),
                        contentDescription = null,
                        tint = RedColor,
                        modifier = Modifier.size(24.dp)
                    )
                },
                onClick = {
                    onDeleteClick()
                }
            )
        }
    }
}