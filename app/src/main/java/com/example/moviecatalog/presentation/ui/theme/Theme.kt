package com.example.moviecatalog.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

private val DarkColorScheme = darkColorScheme(
    primary = AccentColor,
    secondary = PurpleGrey80,
    onSecondary = WhiteColor,
    tertiary = WhiteColor,
    background = BackgroundColor,
    surface = StatusBarColor,
    onPrimary = WhiteColor,
    primaryContainer = AccentColor,
    outline = DarkGrayColor

)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)


@Composable
fun MovieCatalogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor = colorScheme.surface.toArgb()
    window.navigationBarColor = colorScheme.surface.toArgb()
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme

    val windowInsetsController =
        ViewCompat.getWindowInsetsController(window.decorView) ?: return
    windowInsetsController.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}