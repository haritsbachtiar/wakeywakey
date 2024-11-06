package org.example.project.alarm.presentations

import org.example.project.alarm.presentations.model.AlarmUI

data class AlarmsState(
    val alarms: List<AlarmUI> = emptyList()
)
