package org.example.project.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AlarmTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
//        darkTheme -> DarkColors
//        else -> LightColors
//    }
    MaterialTheme(
        typography = Typography,
        content = content
    )
}