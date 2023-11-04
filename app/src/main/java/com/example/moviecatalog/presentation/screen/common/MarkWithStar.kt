package com.example.moviecatalog.presentation.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.common.MarkSelector

@Composable
fun MarkWithStar(value: Int){
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = MarkSelector.setColorForMark(value.toFloat()),
                shape = RoundedCornerShape(35.dp)
            )
    ){
        Row(
            modifier = Modifier
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.star_mark),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp)
            )
            Text(
                text = value.toString(),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 2.dp, end = 4.dp)
            )
        }
    }
}