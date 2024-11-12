package org.example.project.alarm.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.alarm.presentations.model.AlarmUI

class AlarmsViewModel(
    private val alarmDataSource: AlarmDataSource
) : ViewModel() {
    private var _state = MutableStateFlow(AlarmsState())
    val state = _state
        .onStart { loadAlarms() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AlarmsState()
        )


    fun onAction(action: AlarmsAction) {
        when (action) {
            is AlarmsAction.OnAlarmClick -> {
                selectAlarm(alarmUI = action.alarmUI)
            }

            is AlarmsAction.OnAlarmsCreate -> {
                createAlarm(alarmUI = action.alarmUI)
            }

            is AlarmsAction.OnAlarmsDelete -> {
                deleteAlarm(alarmUI = action.alarmUI)
            }

            is AlarmsAction.OnAlarmsUpdate -> {
                updateAlarm(alarmUI = action.alarmUI)
            }

            is AlarmsAction.UpdateAlarmName -> {
                updateAlarmName(action.name)
            }

            is AlarmsAction.UpdateAlarmTime -> {
                updateAlarmTime(action.hour, action.minute)
            }

            is AlarmsAction.OnAlarmClear -> {
                clearAlarm()
            }
        }
    }

    private fun selectAlarm(alarmUI: AlarmUI) {
        _state.update { it.copy(selectedAlarms = alarmUI) }
    }

    private fun createAlarm(alarmUI: AlarmUI) {
        //    alarmDataSource.writeAlarm(alarmUI.name, alarmUI.hourDisplay)
    }

    private fun updateAlarm(alarmUI: AlarmUI) {
        // TODO
    }

    private fun deleteAlarm(alarmUI: Any) {
        // TODO
    }

    private fun loadAlarms() {
        // TODO LOAD ALARMS
    }

    private fun updateAlarmName(name: String) {
        _state.update {
            val selectedAlarms = it.selectedAlarms
            // Only update if `name` has changed
            if (selectedAlarms == null) {
                it.copy(selectedAlarms = AlarmUI(name = name))
            } else if (selectedAlarms.name != name) {
                it.copy(
                    selectedAlarms = selectedAlarms.copy(name = name)
                )
            } else {
                it
            }

        }
    }

    private fun updateAlarmTime(hour: Int, minute: Int) {
        _state.update {
            val selectedAlarms = it.selectedAlarms
            if (selectedAlarms == null) {
                it.copy(selectedAlarms = AlarmUI(hour = hour, minute = minute))
            } else if (selectedAlarms.hour != hour || selectedAlarms.minute != minute) {
                it.copy(
                    selectedAlarms = selectedAlarms.copy(hour = hour, minute = minute)
                )
            } else {
                it
            }
        }
    }

    private fun clearAlarm() {
        _state.update {
            it.copy(selectedAlarms = null)
        }
    }
}