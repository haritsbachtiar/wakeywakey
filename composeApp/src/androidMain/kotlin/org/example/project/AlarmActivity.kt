package org.example.project

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.example.project.alarm.presentations.trigger.AlarmTriggerScreen

class AlarmActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hour = this.intent.getStringExtra("hour").orEmpty()
        val minute = this.intent.getStringExtra("minute").orEmpty()

        val mediaPlayer = MediaPlayer.create(this@AlarmActivity, R.raw.camelot)
        mediaPlayer.start()

        setContent {
            enableEdgeToEdge()
            AlarmTriggerScreen(
                alarmName = "Test Name",
                alarmTime = "$hour:$minute",
                onClick = {
                    mediaPlayer.release()
                    this@AlarmActivity.startActivity(Intent(this@AlarmActivity, MainActivity::class.java))
                }
            )
        }
    }
}