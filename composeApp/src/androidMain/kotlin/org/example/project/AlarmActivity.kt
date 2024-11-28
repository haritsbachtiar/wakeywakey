package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.example.project.alarm.presentations.trigger.AlarmTriggerScreen

class AlarmActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            enableEdgeToEdge()
            AlarmTriggerScreen(
                alarmName = "Test Name",
                alarmTime = "Alarm Time",
            ) {}
        }
    }
}