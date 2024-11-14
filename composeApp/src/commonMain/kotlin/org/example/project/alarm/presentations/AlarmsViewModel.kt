package org.example.project.alarm.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.alarm.data.tables.AlarmRealmObject
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.alarm.presentations.model.AlarmUI
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class AlarmsViewModel(
    private val alarmDataSource: AlarmDataSource
) : ViewModel() {
    private var _alarmState = MutableStateFlow(AlarmsState())
    val alarmState = _alarmState
        .onStart { loadAlarms() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            AlarmsState()
        )


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
        val selectedAlarmRealmObject = AlarmRealmObject()
        selectedAlarmRealmObject.name = selectedAlarmUI.name
        selectedAlarmRealmObject.minute = selectedAlarmUI.minute
        selectedAlarmRealmObject.hour = selectedAlarmUI.hour
        selectedAlarmRealmObject.isActive = selectedAlarmUI.isActive

        viewModelScope.launch {
            alarmDataSource.writeAlarm(selectedAlarmRealmObject)
        }
    }

    private fun updateAlarm() {
        val selectedAlarmUI = _alarmState.value.selectedAlarms ?: AlarmUI()
        if (selectedAlarmUI._id != null) {
            val selectedAlarm = AlarmRealmObject()
            selectedAlarm._id = selectedAlarmUI._id
            selectedAlarm.name = selectedAlarmUI.name
            selectedAlarm.minute = selectedAlarmUI.minute
            selectedAlarm.hour = selectedAlarmUI.hour
            selectedAlarm.isActive = selectedAlarmUI.isActive

            viewModelScope.launch {
                alarmDataSource.updateAlarm(selectedAlarm)
            }
        }
    }

    private fun deleteAlarm(alarmUI: AlarmUI) {
        if (alarmUI._id != null) {
            val selectedAlarm = AlarmRealmObject()
            selectedAlarm._id = alarmUI._id
            selectedAlarm.name = alarmUI.name
            selectedAlarm.minute = alarmUI.minute
            selectedAlarm.hour = alarmUI.hour
            selectedAlarm.isActive = alarmUI.isActive

            viewModelScope.launch {
                alarmDataSource.deleteAlarms(selectedAlarm)
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
                _alarmState.update { alarmsState ->
                    alarmsState.copy(
                        isLoading = false,
                        alarms = listOfAlarms.map { alarmTable ->
                            AlarmUI(
                                _id = alarmTable._id,
                                name = alarmTable.name,
                                minute = alarmTable.minute,
                                hour = alarmTable.hour,
                                isActive = alarmTable.isActive
                            )
                        }
                    )
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

    private fun clearAlarm() {
        _alarmState.update {
            it.copy(selectedAlarms = null)
        }
    }
}