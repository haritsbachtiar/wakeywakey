package org.example.project.alarm.data

import android.content.Context
import android.media.RingtoneManager
import org.example.project.WakeyWakeyApplication

class AndroidEnableAlarmSound(private val context: Context) : EnableAlarmSound {

    private val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

    override fun startAlarm() {
        RingtoneManager.getRingtone(context, ringtoneUri)
            .play()
    }

    override fun stopAlarm() {
        RingtoneManager.getRingtone(context, ringtoneUri)
            .stop()
    }
}

actual fun getEnableAlarmSound(): EnableAlarmSound =
    AndroidEnableAlarmSound(context = WakeyWakeyApplication().applicationContext)