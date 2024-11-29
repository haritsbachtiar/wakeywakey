package org.example.project.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import org.example.project.AlarmActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmName = intent?.getStringExtra("name")
        val newIntent = Intent(context, AlarmActivity::class.java).apply {
            putExtra("name", alarmName)
            putExtra("hour", intent?.getIntExtra("hour", 0))
            putExtra("minute", intent?.getIntExtra("minute", 0))
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            context?.startActivity(newIntent)
        } else {
            val notificationHandler = NotificationHandler(context!!)
            notificationHandler.start(newIntent, alarmName ?: "WAKEY WAKEY", "Alarm Ringing")
        }
    }
}