package org.example.project

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.LocalTime
import org.example.project.alarm.data.AlarmScheduler
import org.example.project.alarm.data.tables.AlarmRealmObject
import org.example.project.data.AlarmReceiver

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


actual class AndroidAlarmSchedulerImp(private val context: Context): AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    @RequiresApi(Build.VERSION_CODES.S)
    actual override fun schedule(alarmItem: AlarmRealmObject) {
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

    actual override fun cancel(alarmItem: AlarmRealmObject) {
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
