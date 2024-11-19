package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.core.presentation.theme.AlarmTheme
import org.example.project.alarm.WakeyWakeyApp
import org.koin.core.context.startKoin

@Composable
fun App(isAlarmRinging: Boolean, hour: String, minute: String) {
    AlarmTheme {
        WakeyWakeyApp(isAlarmRinging, hour, minute)
    }
}