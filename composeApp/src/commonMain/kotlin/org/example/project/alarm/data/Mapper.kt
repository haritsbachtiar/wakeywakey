package org.example.project.alarm.data

import org.example.project.alarm.data.tables.AlarmRealmObject
import org.example.project.alarm.presentations.model.AlarmUI

fun AlarmUI.toAlarmRealmObject(): AlarmRealmObject {
    val alarmRealmObject = AlarmRealmObject()
    if (_id != null) {
        alarmRealmObject._id = _id
    }
    alarmRealmObject.name = name
    alarmRealmObject.minute = minute
    alarmRealmObject.hour = hour
    alarmRealmObject.isActive = isActive
    return alarmRealmObject
}

fun AlarmRealmObject.toAlarmUI(): AlarmUI {
    return AlarmUI(
        _id = _id,
        name = name,
        minute = minute,
        hour = hour,
        isActive = isActive
    )
}