package org.example.project.alarm.data

import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class CountDownHelper {
    fun calculateCountdown(
        hour: Int,
        minute: Int,
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): String {
        val currentTime = Clock.System.now().toLocalDateTime(timeZone)
        val targetDateTime = getTargetDateTime(hour, minute, currentTime)

        // Calculate the duration between current time and target time
        val duration = targetDateTime.toInstant(timeZone) - currentTime.toInstant(timeZone)
        val totalSeconds = duration.inWholeSeconds

        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
//        val seconds = totalSeconds % 60
        return if (hours > 0) {
            "${hours}h ${minutes}min"
        }else {
            "${minutes}m"
        }
//        return "${hours}h ${minutes}m ${seconds}s"
    }

    private fun getTargetDateTime(hour: Int, minute: Int, currentTime: LocalDateTime): LocalDateTime {
        val targetToday = LocalDateTime(
            year = currentTime.year,
            month = currentTime.month,
            dayOfMonth = currentTime.dayOfMonth,
            hour = hour,
            minute = minute
        )

        return if (targetToday > currentTime) {
            // If the target time is later today, use it
            targetToday
        } else {
            // Otherwise, move the target to the next day
            val nextDay = currentTime.date.plus(DatePeriod(days = 1))
            LocalDateTime(
                year = nextDay.year,
                month = nextDay.month,
                dayOfMonth = nextDay.dayOfMonth,
                hour = hour,
                minute = minute
            )
        }
    }
}