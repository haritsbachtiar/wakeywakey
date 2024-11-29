package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.alarm.WakeyWakeyApp
import org.example.project.core.presentation.theme.AppTheme

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        WakeyWakeyApp()
    }
}