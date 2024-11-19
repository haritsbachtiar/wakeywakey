package org.example.project.alarm.presentations

import org.example.project.alarm.presentations.model.AlarmUI

data class AlarmsState(
    val isLoading: Boolean = false,
    val alarms: List<AlarmUI> = emptyList(),
    val selectedAlarms: AlarmUI? = null,
    val isAlarmRinging: Boolean = false
)
