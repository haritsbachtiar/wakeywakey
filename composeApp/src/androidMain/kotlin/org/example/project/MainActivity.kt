package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.example.project.alarm.presentations.detail.component.AlarmTimePickerDialog
import org.example.project.alarm.presentations.trigger.AlarmTriggerScreen
import org.example.project.core.presentation.theme.AlarmTheme
import org.example.project.data.AlarmReceiver

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isAlarmRinging = this.intent.getBooleanExtra("alarm", false)
        val hour = this.intent.getStringExtra("hour").orEmpty()
        val minute = this.intent.getStringExtra("minute").orEmpty()

        installSplashScreen()
        setContent {
            App(isAlarmRinging = isAlarmRinging, hour = hour, minute = minute)
        }
    }
}

@Preview
@Composable
fun AlarmListPreview() {
    AlarmTheme {
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
     /*   AlarmTimePickerDialog(
            onConfirm = { _, _ -> },
            onDismiss = { *//*TODO*//* }
        )*/
    }
}