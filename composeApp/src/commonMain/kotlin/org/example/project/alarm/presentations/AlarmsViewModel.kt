package org.example.project.alarm.presentations

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import org.example.project.alarm.data.AlarmDataSource

class AlarmsViewModel(
    private val dataSource: AlarmDataSource
): ViewModel() {
    var _state = MutableStateFlow(AlarmsState())
    val state = _state
        .onStart { loadAlarms() }

    private fun loadAlarms() {
    }
}