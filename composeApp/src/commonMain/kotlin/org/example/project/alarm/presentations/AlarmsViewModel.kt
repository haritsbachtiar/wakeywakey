package org.example.project.alarm.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.alarm.data.AlarmScheduler
import org.example.project.alarm.data.CountDownHelper
import org.example.project.alarm.data.toAlarmRealmObject
import org.example.project.alarm.data.toAlarmUI
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.alarm.presentations.model.AlarmUI

class AlarmsViewModel(
    private val alarmDataSource: AlarmDataSource,
    private val alarmScheduler: AlarmScheduler,
    private val countDownHelper: CountDownHelper
) : ViewModel() {
    private var _alarmState = MutableStateFlow(AlarmsState())
    val alarmState = _alarmState
        .onStart { loadAlarms() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            AlarmsState()
        )

    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCleared() {
        super.onCleared()
        scope.cancel() // Clean up the coroutine
    }

    fun onAction(action: AlarmsAction) {
        when (action) {
            is AlarmsAction.OnAlarmClick -> {
                selectAlarm(alarmUI = action.alarmUI)
            }

            is AlarmsAction.OnAlarmsCreate -> {
                createAlarm()
            }

            is AlarmsAction.OnAlarmsDelete -> {
                deleteAlarm(alarmUI = action.alarmUI)
            }

            is AlarmsAction.OnAlarmsUpdate -> {
                updateAlarm()
            }

            is AlarmsAction.UpdateAlarmName -> {
                updateAlarmName(action.name)
            }

            is AlarmsAction.UpdateAlarmTime -> {
                updateAlarmTime(action.hour, action.minute)
            }

            is AlarmsAction.UpdateAlarmStatus -> {
                updateAlarmStatus(action.alarmUI)
            }

            is AlarmsAction.OnAlarmClear -> {
                clearAlarm()
            }
        }
    }

    private fun selectAlarm(alarmUI: AlarmUI) {
        _alarmState.update { it.copy(selectedAlarms = alarmUI) }
    }

    private fun createAlarm() {
        val selectedAlarmUI = _alarmState.value.selectedAlarms ?: AlarmUI()
        viewModelScope.launch {
            alarmDataSource.writeAlarm(selectedAlarmUI.toAlarmRealmObject())
        }
    }

    private fun updateAlarm() {
        val selectedAlarmUI = _alarmState.value.selectedAlarms ?: AlarmUI()
        if (selectedAlarmUI._id != null) {
            viewModelScope.launch {
                alarmDataSource.updateAlarm(selectedAlarmUI.toAlarmRealmObject())
                if (selectedAlarmUI.isActive) {
                    alarmScheduler.schedule(selectedAlarmUI.toAlarmRealmObject())
                }
            }
        }
    }

    private fun deleteAlarm(alarmUI: AlarmUI) {
        if (alarmUI._id != null) {
            viewModelScope.launch {
                alarmDataSource.deleteAlarms(alarmUI.toAlarmRealmObject())
                alarmScheduler.cancel(alarmUI.toAlarmRealmObject())
            }
        }
    }

    private fun loadAlarms() {
        viewModelScope.launch {
            _alarmState.update { alarmsState ->
                alarmsState.copy(
                    isLoading = true
                )
            }

            alarmDataSource.getAlarms().collectLatest { listOfAlarms ->
                scope.launch {
                    while (true) {
                        val newList = listOfAlarms.map { alarms ->
                            alarms.toAlarmUI(
                                "Alarm in ${countDownHelper.calculateCountdown(alarms.hour, alarms.minute)}"
                            )
                        }
                        _alarmState.update { alarmsState ->
                            alarmsState.copy(
                                isLoading = false,
                                alarms = newList
                            )
                        }
                        delay(60000)
                    }
                }
            }
        }
    }

    private fun updateAlarmName(name: String) {
        _alarmState.update {
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
        _alarmState.update {
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

    private fun updateAlarmStatus(alarmUI: AlarmUI) {
        if (alarmUI._id != null) {
            viewModelScope.launch {
                alarmDataSource.updateAlarm(alarmUI.toAlarmRealmObject())
                if (alarmUI.isActive) {
                    alarmScheduler.schedule(alarmUI.toAlarmRealmObject())
                } else {
                    alarmScheduler.cancel(alarmUI.toAlarmRealmObject())
                }
            }
        }
    }

    private fun clearAlarm() {
        _alarmState.update {
            it.copy(selectedAlarms = null)
        }
    }
}