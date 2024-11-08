package org.example.project.alarm.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.alarm.presentations.model.AlarmUI

class AlarmsViewModel(
    private val dataSource: AlarmDataSource
) : ViewModel() {
    var _state = MutableStateFlow(AlarmsState())
    val state = _state
        .onStart { loadAlarms() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AlarmsState()
        )


    fun onAction(action: AlarmsAction) {
        when (action) {
            is AlarmsAction.OnAlarmsClick -> {
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
        }
    }

    private fun selectAlarm(alarmUI: AlarmUI) {
        _state.update { it.copy(selectedAlarms = alarmUI) }
    }

    private fun createAlarm(alarmUI: AlarmUI) {
        // TODO
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
}