package org.example.project.alarm.data

import android.content.Context
import android.media.RingtoneManager

actual class EnableAlarmSound(private val context: Context) {

    private val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

    actual fun startAlarm() {
        RingtoneManager.getRingtone(context, ringtoneUri)
            .play()
    }

    actual fun stopAlarm() {
        RingtoneManager.getRingtone(context, ringtoneUri)
            .stop()
    }
}

