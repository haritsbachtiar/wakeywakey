package org.example.project.alarm.presentations

import org.example.project.alarm.presentations.model.AlarmUI

sealed interface AlarmsAction {
    data class OnAlarmsClick(val alarmUI: AlarmUI): AlarmsAction
    data class OnAlarmsCreate(val alarmUI: AlarmUI): AlarmsAction
    data class OnAlarmsUpdate(val alarmUI: AlarmUI): AlarmsAction
    data class OnAlarmsDelete(val alarmUI: AlarmUI): AlarmsAction
    data class UpdateAlarmName(val name: String): AlarmsAction
}