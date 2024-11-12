package org.example.project.alarm.presentations

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.alarm.presentations.model.AlarmUI
import org.example.project.alarm.presentations.model.DisplayableDateTime

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

            is AlarmsAction.UpdateAlarmName -> {
                updateAlarmName(action.name)
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
                it.copy(
                    selectedAlarms = AlarmUI(
                        name = name,
                        hourDisplay = DisplayableDateTime(
                            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                            "formatted"
                        ),
                        countDownDisplay = DisplayableDateTime(
                            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                            "formatted"
                        ),

                        )
                )
            } else if (selectedAlarms.name != name) {
                it.copy(
                    selectedAlarms = selectedAlarms?.copy(name = name)
                )
            } else {
                it
            }

        }
    }
}