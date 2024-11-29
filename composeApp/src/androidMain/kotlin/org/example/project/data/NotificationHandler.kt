package org.example.project.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import org.example.project.R

class NotificationHandler(
    private val context: Context
) {
    companion object {
        private const val CHANNEL_ID = "active_run"
        private val CHANNEL_NAME = "reset_password"
    }

    private val notificationManager by lazy {
        context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun start(alarmIntent: Intent, alarmName: String, message: String) {
        createNotificationChannel()
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        context.startActivity(alarmIntent)

        val notification = NotificationCompat
            .Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setChannelId(CHANNEL_ID)
            .setContentTitle(alarmName.uppercase())
            .setContentText(message)
            .setFullScreenIntent(pendingIntent, true)
            .build()

        notificationManager.notify(100, notification)
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}