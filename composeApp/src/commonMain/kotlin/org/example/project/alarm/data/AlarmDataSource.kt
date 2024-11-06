package org.example.project.alarm.data

import kotlinx.coroutines.flow.Flow
import org.example.project.alarm.data.tables.AlarmTable

interface AlarmDataSource {
    suspend fun writeAlarm(alarmTime: String, alarmName: String)
    fun getAlarms(): Flow<List<AlarmTable>>
}