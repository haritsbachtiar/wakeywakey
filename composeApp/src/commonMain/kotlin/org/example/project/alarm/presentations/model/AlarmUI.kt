package org.example.project.alarm.presentations.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

data class AlarmUI(
    val name: String,
    val hourDisplay: DisplayableDateTime,
    val countDownDisplay: DisplayableDateTime
)

data class DisplayableDateTime(
    val value: LocalDateTime,
    val formatted: String
) {
    companion object {
        @OptIn(FormatStringsInDatetimeFormats::class)
        private val hourMinuteFormatter = LocalDateTime.Format {
            byUnicodePattern("HH:mm")
        }
    }

    fun LocalDateTime.toHourFormat(): DisplayableDateTime {
        val formatPattern = "HH:mm"
        return DisplayableDateTime(
            value = this,
            formatted = hourMinuteFormatter.format(this)
        )
    }
}
