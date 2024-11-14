package org.example.project

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.datetime.Clock
import org.example.project.alarm.presentations.detail.component.remainingTime
import kotlin.test.BeforeTest
import kotlin.test.Test


class AlarmTimeTest {

    lateinit var fakeClock : Clock

    @BeforeTest
    fun before() {
        fakeClock = FakeClock()
    }

    @Test
    fun testCheckRemainingTime() {
        fakeClock.now()
        assertThat(remainingTime(24,2)).isEqualTo("24h 2 mins")
    }
}


