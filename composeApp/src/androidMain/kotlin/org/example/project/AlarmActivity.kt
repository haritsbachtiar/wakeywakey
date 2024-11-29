package org.example.project

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.example.project.alarm.presentations.toTimeStringFormat
import org.example.project.alarm.presentations.trigger.AlarmTriggerScreen

class AlarmActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = this.intent.getStringExtra("name").orEmpty()
        val hour = this.intent.getIntExtra("hour", 0)
        val minute = this.intent.getIntExtra("minute", 0)

        val mediaPlayer = MediaPlayer.create(this@AlarmActivity, R.raw.alarm)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        setContent {
            enableEdgeToEdge()
            AlarmTriggerScreen(
                alarmName = name,
                alarmTime = "${hour.toTimeStringFormat()}:${minute.toTimeStringFormat()}",
                onClick = {
                    mediaPlayer.stop()
                    mediaPlayer.release()
                    this@AlarmActivity.startActivity(
                        Intent(
                            this@AlarmActivity,
                            MainActivity::class.java
                        )
                    )
                }
            )
        }
    }
}