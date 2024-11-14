package org.example.project

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char

class FakeClock : Clock {
    override fun now(): Instant {
        val customFormat = DateTimeComponents.Format {
            date(LocalDate.Formats.ISO)
            char(' ')
            time(LocalTime.Formats.ISO)
            char(' ')
            offset(UtcOffset.Formats.ISO)
        }
        val instant = Instant.parse("2023-01-02 22:35:01.14 +01:00", customFormat)
        return instant
    }
}