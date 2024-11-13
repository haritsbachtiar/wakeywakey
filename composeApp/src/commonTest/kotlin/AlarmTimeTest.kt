package org.example.project

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.example.project.alarm.presentations.detail.component.remainingTime

import kotlin.test.Test


class AlarmTimeTest {

    @Test
    fun testCheckRemainingTime() {
        assertThat(remainingTime()).isEqualTo("24h 2 mins")
    }
}
