package com.example.moviecatalog.presentation.screen.moviescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviecatalog.R
import com.example.moviecatalog.data.model.Mark
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.BackgroundColor
import com.example.moviecatalog.presentation.ui.theme.BottomBarColor
import com.example.moviecatalog.presentation.ui.theme.ChipColor
import com.example.moviecatalog.presentation.ui.theme.GoodMarkColor
import com.example.moviecatalog.presentation.ui.theme.GrayTextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(backTo: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {  },
                navigationIcon = {
                    IconButton(onClick = { backTo() }) {
                        Icon (
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BottomBarColor
                )
            )
        },

        content = {
            LazyColumn {
                item{
                    PosterWithGradient("https://avatars.mds.yandex.net/get-kinopoisk-image/1898899/f59002c6-6fb6-4747-bf19-039f333d7ce5/1920x")
                }
                item {
                    LabelWithButtonAndMark(Mark(mark = "8.5", color = GoodMarkColor), "Людина-Павук: До дому шляху нема")
                }
                item{
                    MovieDescription("Жизнь и репутация Питера Паркера оказываются под угрозой, поскольку Мистерио раскрыл всему миру тайну личности Человека-паука. Пытаясь исправить ситуацию, Питер обращается за помощью к Стивену Стрэнджу, но вскоре всё становится намного опаснее.")
                }
                item{
                    GenresSection(genres = listOf("боевик", "фантастика", "драма", "мелодрама"))
                }
                item{
                    MovieDetailsSection()
                }
                item{
                    MovieReviewsSection()
                }
            }
        }
    )
}

@Composable
fun PosterWithGradient(url: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, BackgroundColor),
                        startY = size.height * 0.7f,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient)
                    }
                }
        )
    }
}

@Composable
fun LabelWithButtonAndMark(mark: Mark, movieName: String, ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = mark.color,
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Text(
                text = mark.mark,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 14.dp, top = 4.dp, end = 14.dp, bottom = 4.dp)
            )
        }

        Text(
            text = movieName,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .background(
                    color = ChipColor,
                    shape = CircleShape
                )
                .padding(10.dp),
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(24.dp),
                content = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.like_unfocused),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        }
    }
}

@Composable
fun MovieDescription(description: String) {
    var showFullDescription by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = description,
            fontSize = 15.sp,
            maxLines = if (showFullDescription) Int.MAX_VALUE else 4,
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
                        if (!showFullDescription) {
                            drawRect(gradient)
                        }
                    }
                }
        )

        Text(
            text = if (showFullDescription) "Свернуть ▲" else "Подробнее ▼",
            fontSize = 15.sp,
            color = AccentColor,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable {
                    showFullDescription = !showFullDescription
                }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenresSection(genres: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Жанры",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genres.forEach { genre ->
                GenreItem(genre = genre)
            }
        }
    }
}

@Composable
fun GenreItem(genre: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(AccentColor)
    ) {
        Text(
            text = genre,
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
        )
    }
}

data class MovieDetail(val type: String, val value: String)

@Composable
fun MovieDetailRow(movieDetail: MovieDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = movieDetail.type,
            fontSize = 14.sp,
            color = GrayTextColor,
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = movieDetail.value,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun MovieDetailsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "О фильме",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        MovieDetailRow(MovieDetail("Год", "1999"))
        MovieDetailRow(MovieDetail("Страна", "США, Австралия"))
        MovieDetailRow(MovieDetail("Слоган", "«Добро пожаловать в реальный мир»"))
        MovieDetailRow(MovieDetail("Режиссёр", "Лана Вачовски, Лилли Вачовски"))
        MovieDetailRow(MovieDetail("Бюджет", "$63 000 000"))
        MovieDetailRow(MovieDetail("Сборы в мире", "$463 517 383"))
        MovieDetailRow(MovieDetail("Возраст", "16+"))
        MovieDetailRow(MovieDetail("Время", "136 мин"))
    }
}

@Composable
fun MovieReviewsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Отзывы",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        MovieReview()
        MovieReview()
        MovieReview()
    }

}

@Composable
fun MovieReview(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://avatars.mds.yandex.net/get-kinopoisk-image/1898899/f59002c6-6fb6-4747-bf19-039f333d7ce5/1920x",
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Анонимный пользователь",
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
            
            MarkWithStar()
        }


        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "Фильм треш и кринж, для мамонтов. Полный скам и кликбейт на просмотр!",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Start
            )

            Text(
                text = "07.10.2023",
                fontSize = 12.sp,
                color = GrayTextColor,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun MarkWithStar(){
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = GoodMarkColor,
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
                text = "8",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 2.dp, end = 4.dp)
            )
        }
    }
}




