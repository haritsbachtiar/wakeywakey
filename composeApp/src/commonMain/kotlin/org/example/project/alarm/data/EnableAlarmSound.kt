package org.example.project.alarm.data

interface EnableAlarmSound {
    fun startAlarm()
    fun stopAlarm()
}

expect fun getEnableAlarmSound():EnableAlarmSound