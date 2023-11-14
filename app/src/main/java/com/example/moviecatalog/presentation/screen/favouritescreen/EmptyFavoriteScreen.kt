package com.example.moviecatalog.presentation.screen.favouritescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.presentation.ui.theme.Values.BigPadding
import com.example.moviecatalog.presentation.ui.theme.Values.MoreSpaceBetweenObjects

@Composable
fun EmptyFavouriteScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text (
                text = stringResource(R.string.empty_favourite1),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W700),
                modifier = Modifier.padding(
                    bottom = 5.dp,
                    start = BigPadding,
                    end = BigPadding
                )
            )

            Text (
                text = stringResource(R.string.empty_favourite2),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W400),
                modifier = Modifier.padding(horizontal = MoreSpaceBetweenObjects)
            )
        }
    }
}