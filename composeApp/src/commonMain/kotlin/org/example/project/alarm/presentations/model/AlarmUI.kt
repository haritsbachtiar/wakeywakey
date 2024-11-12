package org.example.project.alarm.presentations.model

data class AlarmUI(
    val name: String = "",
    val hour: Int = 0,
    val minute: Int = 0,
    val isActive: Boolean = false
)
