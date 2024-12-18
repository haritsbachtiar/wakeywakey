package org.example.project

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import androidx.annotation.RequiresApi
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.example.project.alarm.data.AlarmScheduler
import org.example.project.alarm.data.tables.AlarmRealmObject
import org.example.project.data.AlarmReceiver
import kotlin.time.Duration

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


actual class AlarmSchedulerImp(private val context: Context): AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    @RequiresApi(Build.VERSION_CODES.S)
    actual override fun schedule(alarmItem: AlarmRealmObject) {
        val canScheduleExactAlarms = alarmManager.canScheduleExactAlarms()
        if (canScheduleExactAlarms.not()) {
            Intent().also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.action = ACTION_REQUEST_SCHEDULE_EXACT_ALARM
                context.startActivity(intent)
            }
        }

        val canDrawOverlay = Settings.canDrawOverlays(context)
        if (canDrawOverlay.not()) {
            Intent().also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.action = ACTION_MANAGE_OVERLAY_PERMISSION
                context.startActivity(intent)
            }
        }

        val currentInstant = Clock.System.now()
        val currentDateTime = currentInstant.toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
        val alarmInstant = currentInstant
            .minus(currentDateTime.hour, DateTimeUnit.HOUR)
            .minus(currentDateTime.minute, DateTimeUnit.MINUTE)
            .minus(currentDateTime.second, DateTimeUnit.SECOND)
            .plus(alarmItem.hour, DateTimeUnit.HOUR)
            .plus(alarmItem.minute, DateTimeUnit.MINUTE)

        val alarmTriggerInstant = if (alarmInstant < currentInstant) {
            alarmInstant.plus(24, DateTimeUnit.HOUR)
        } else {
            alarmInstant
        }

        val triggerAtMs = alarmTriggerInstant.toEpochMilliseconds()

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("name", alarmItem.name)
            putExtra("hour", alarmItem.hour)
            putExtra("minute", alarmItem.minute)
        }
        println("BEFORE ALARM SAVED ${alarmManager.nextAlarmClock}")
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
        println("ALARM SAVED ${alarmManager.nextAlarmClock}")
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
