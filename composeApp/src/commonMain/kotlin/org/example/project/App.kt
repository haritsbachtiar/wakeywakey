package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.alarm.WakeyWakeyApp
import org.example.project.core.presentation.theme.AppTheme

@Composable
fun App(
    isAlarmRinging: Boolean,
    hour: String,
    minute: String,
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        WakeyWakeyApp(isAlarmRinging, hour, minute)
    }
}