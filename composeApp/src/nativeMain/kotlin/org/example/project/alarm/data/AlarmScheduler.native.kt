package org.example.project.alarm.data

import org.example.project.alarm.data.tables.AlarmRealmObject

class NativeAlarmScheduler : AlarmScheduler {
    override fun schedule(alarmItem: AlarmRealmObject) {
        TODO("Not yet implemented")
    }

    override fun cancel(alarmItem: AlarmRealmObject) {
        TODO("Not yet implemented")
    }

}

actual fun getAlarmScheduler(): AlarmScheduler = NativeAlarmScheduler()