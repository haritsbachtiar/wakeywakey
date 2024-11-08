package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.core.presentation.theme.AlarmTheme
import org.example.project.feature.alarm.list.AlarmListScreen
import org.example.project.feature.alarm.model.AlarmUI
import org.example.project.feature.alarm.model.DisplayableDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AlarmListPreview() {
    val alarms = listOf(
        AlarmUI(
            name = "Wake Up",
            hourDisplay = DisplayableDateTime(
                value = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                formatted = "10:00"
            ),
            countDownDisplay = DisplayableDateTime(
                value = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                formatted = "Alarm in 30min"
            ),
        ),
        AlarmUI(
            name = "Education",
            hourDisplay = DisplayableDateTime(
                value = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                formatted = "04.00"
            ),
            countDownDisplay = DisplayableDateTime(
                value = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                formatted = "Alarm in 5h 30min"
            ),
        ),
        AlarmUI(
            name = "Wake Up",
            hourDisplay = DisplayableDateTime(
                value = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                formatted = "06:00"
            ),
            countDownDisplay = DisplayableDateTime(
                value = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                formatted = "Alarm in 6h 30min"
            ),
        ),
    )
    AlarmTheme {
        AlarmListScreen(
            alarms = listOf(),
            onCardClick = {}
        )
    }
}