package org.example.project

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.example.project.alarm.presentations.detail.component.remainingTime
import kotlin.test.BeforeTest

import kotlin.test.Test

class AlarmTimeTest {
    private lateinit var fakeTime: String
    private lateinit var fakeClock: FakeClock
    @BeforeTest
    fun setUp() {
        fakeTime = "2024-11-24T19:12:00Z"
        fakeClock = FakeClock(fakeTime)
    }

    @Test
    fun testFakeClock() {
        assertThat(fakeClock.now().toString())
            .isEqualTo(fakeTime)
    }
    @Test
    fun testCheckRemainingTimeWhenEarlierThanAlarm() {
        println(fakeClock)
        assertThat(remainingTime(20,20, fakeClock))
            .isEqualTo("18h 8m")
    }
    @Test
    fun testCheckRemainingTimeWhenLaterThanAlarm() {
        println(fakeClock)
        assertThat(remainingTime(14,20, fakeClock))
            .isEqualTo("12h 8m")
    }
}
