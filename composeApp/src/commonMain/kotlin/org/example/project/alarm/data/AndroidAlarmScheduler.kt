package org.example.project.alarm.data

import org.example.project.alarm.data.tables.AlarmRealmObject

expect class AndroidAlarmScheduler() : AlarmScheduler {

    override fun schedule(alarmItem: AlarmRealmObject)

    override fun cancel(alarmItem: AlarmRealmObject)
}