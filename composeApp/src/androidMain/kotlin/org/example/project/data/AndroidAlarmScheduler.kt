package org.example.project.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.LocalTime
import org.example.project.alarm.data.AlarmScheduler
import org.example.project.alarm.data.tables.AlarmTable

class AndroidAlarmScheduler(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    @RequiresApi(Build.VERSION_CODES.S)
    override fun schedule(alarmItem: AlarmTable) {
        val alarmTime = LocalTime(
            hour = alarmItem.hour,
            minute = alarmItem.minute,
            second = 0,
            nanosecond = 0)

        val triggerAtMs = alarmTime.toMillisecondOfDay().toLong()
        val intent = Intent(context, AlarmReceiver::class.java)

        if(alarmManager.canScheduleExactAlarms()) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAtMs,
                PendingIntent.getBroadcast(
                    context,
                    alarmItem.hashCode(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
        }
    }

    override fun cancel(alarmItem: AlarmTable) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                Intent(),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}