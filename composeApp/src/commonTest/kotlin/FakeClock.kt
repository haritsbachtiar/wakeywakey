package org.example.project

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class FakeClock(val time: String): Clock {
    override fun now(): Instant {
        return Instant.parse(this.time)
    }
}