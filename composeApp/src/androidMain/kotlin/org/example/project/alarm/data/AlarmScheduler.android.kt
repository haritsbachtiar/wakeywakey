package org.example.project.alarm.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.LocalTime
import org.example.project.WakeyWakeyApplication
import org.example.project.alarm.data.tables.AlarmRealmObject
import org.example.project.data.AlarmReceiver
import org.koin.core.context.KoinContext

class AndroidAlarmScheduler(private val context: Context): AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    @RequiresApi(Build.VERSION_CODES.S)
    override fun schedule(alarmItem: AlarmRealmObject) {
        val alarmTime = LocalTime(
            hour = alarmItem.hour,
            minute = alarmItem.minute,
            second = 0,
            nanosecond = 0)

        val triggerAtMs = alarmTime.toMillisecondOfDay().toLong()

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("hour", alarmItem.hour)
            putExtra("minute", alarmItem.minute)
        }

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

    override fun cancel(alarmItem: AlarmRealmObject) {
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

actual fun getAlarmScheduler(): AlarmScheduler = AndroidAlarmScheduler(context = KoinContext())