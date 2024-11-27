package org.example.project

import org.example.project.alarm.data.AlarmScheduler
import org.example.project.alarm.data.tables.AlarmRealmObject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect class AlarmSchedulerImp: AlarmScheduler {
    override fun schedule(alarmItem: AlarmRealmObject)
    override fun cancel(alarmItem: AlarmRealmObject)
}