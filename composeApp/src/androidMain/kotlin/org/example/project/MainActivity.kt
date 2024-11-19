package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.example.project.core.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true
            )
        }
    }
}

@Preview
@Composable
fun AlarmListPreview() {
    AppTheme(darkTheme = false, dynamicColor = false) {
//        AlarmDetailScreen(
//            AlarmsState(),
//            {},
//            modifier = Modifier.background(Color.Gray)
//        )
//        AlarmTime(
//            hour = "16",
//            minutes = "45",
//            description = "Alarm in 7h 15 min"
//        )
//        AlarmNameDialog(
//            name = "works",
//            onSave = {},
//            onDismissRequest = { /*TODO*/ }
//        )
//        AlarmTimePickerDialog(
//            initialHour = null,
//            initialMinute = null,
//            onConfirm = { _, _ -> },
//            onDismiss = { }
//        )
        Column {
            Text("12345678")
        }
    }
}