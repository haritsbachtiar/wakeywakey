package org.example.project.alarm.data

import org.example.project.alarm.data.tables.AlarmRealmObject

interface AlarmScheduler {
    fun schedule(alarmItem: AlarmRealmObject)
    fun cancel(alarmItem: AlarmRealmObject)
}
