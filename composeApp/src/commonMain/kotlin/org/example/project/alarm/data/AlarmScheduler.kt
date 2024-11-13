package org.example.project.alarm.data

import org.example.project.alarm.data.tables.AlarmTable

interface AlarmScheduler {
    fun schedule(alarmItem: AlarmTable)
    fun cancel(alarmItem: AlarmTable)
}