package org.example.project.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.example.project.MainActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        /** Navigate to the AlarmTriggerScreen */
        val alarmIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK // Required to start an Activity from a Receiver
        }

        val hour = intent?.getStringExtra("hour")
        val minute = intent?.getStringExtra("minute")

        alarmIntent.putExtra("alarm", true)
        alarmIntent.putExtra("hour", hour)
        alarmIntent.putExtra("minute", minute)

        context?.startActivity(alarmIntent)
    }
}