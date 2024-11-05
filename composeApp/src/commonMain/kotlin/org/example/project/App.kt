package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.core.presentation.theme.AlarmTheme
import org.example.project.feature.WakeyWakeyApp

@Composable
fun App() {
    AlarmTheme {
        WakeyWakeyApp()
    }
}