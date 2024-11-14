package org.example.project.alarm.domain

import kotlinx.coroutines.flow.Flow
import org.example.project.alarm.data.tables.AlarmRealmObject

interface AlarmDataSource {
    suspend fun writeAlarm(alarmTime: AlarmRealmObject)
    suspend fun updateAlarm(alarmRealmObject: AlarmRealmObject)
    suspend fun deleteAlarms(alarmRealmObject: AlarmRealmObject)
    fun getAlarms(): Flow<List<AlarmRealmObject>>
}