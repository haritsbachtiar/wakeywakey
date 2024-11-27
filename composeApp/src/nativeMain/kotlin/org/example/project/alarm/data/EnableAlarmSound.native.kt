package org.example.project.alarm.data

class NativeEnableAlarmSound(): EnableAlarmSound {
    override fun startAlarm() {
        TODO("Not yet implemented")
    }

    override fun stopAlarm() {
        TODO("Not yet implemented")
    }

}

actual fun getEnableAlarmSound(): EnableAlarmSound = NativeEnableAlarmSound()