package org.example.project.alarm.domain

import kotlinx.coroutines.flow.Flow
import org.example.project.alarm.data.tables.AlarmTable

interface AlarmDataSource {
    suspend fun writeAlarm(alarmTime: String, alarmName: String)
    suspend fun updateAlarm(alarmTable: AlarmTable)
    suspend fun deleteAlarms(alarmTable: AlarmTable)
    fun getAlarms(): Flow<List<AlarmTable>>
}