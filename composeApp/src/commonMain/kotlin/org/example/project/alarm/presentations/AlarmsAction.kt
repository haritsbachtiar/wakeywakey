package org.example.project.alarm.presentations

import org.example.project.alarm.presentations.model.AlarmUI

sealed interface AlarmsAction {
    data class OnAlarmClick(val alarmUI: AlarmUI) : AlarmsAction
    data object OnAlarmsCreate : AlarmsAction
    data object OnAlarmsUpdate : AlarmsAction
    data class OnAlarmsDelete(val alarmUI: AlarmUI) : AlarmsAction
    data class UpdateAlarmName(val name: String) : AlarmsAction
    data class UpdateAlarmTime(val hour: Int, val minute: Int) : AlarmsAction
    data object OnAlarmClear : AlarmsAction
}