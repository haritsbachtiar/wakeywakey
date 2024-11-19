package org.example.project

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class Clock(private val fixedInstant: Instant) : Clock {
    class System(private val fixedInstant: Instant) : Clock {
        override fun now(): Instant = fixedInstant
    }

    override fun now(): Instant {
        TODO("Not yet implemented")
    }
}

fun Clock.Companion.System(fixedInstant: Instant): Clock = Clock(fixedInstant)