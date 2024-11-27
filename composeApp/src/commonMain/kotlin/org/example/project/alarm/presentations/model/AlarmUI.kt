package org.example.project.alarm.presentations.model

import org.mongodb.kbson.ObjectId

data class AlarmUI(
    val _id: ObjectId? = null,
    val name: String = "",
    val hour: Int = 0,
    val minute: Int = 0,
    val isActive: Boolean = false
)
