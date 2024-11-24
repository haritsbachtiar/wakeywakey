package org.example.project.alarm.presentations.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration

@Composable
fun AlarmTime(
    modifier: Modifier = Modifier,
    hour: Int,
    minutes: Int,
    description: String,
    onClick: () -> Unit
) {
    val countdownState = remember { mutableStateOf(description) }

    Column(
        modifier = modifier
            .clickable {
                onClick.invoke()
            }
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(10.dp))
                    .padding(vertical = 16.dp, horizontal = 38.dp),
                text = hour.toTimeStringFormat(),
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = ":",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(10.dp))
                    .padding(vertical = 16.dp, horizontal = 38.dp),
                text = minutes.toTimeStringFormat(),
                style = MaterialTheme.typography.displayLarge
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        val remainingTime = remainingTime(hour, minutes, Clock.System);
        Text(
            text = "Alarm in $remainingTime",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

fun remainingTime(alarmHours: Int, alarmMinutes: Int, clock: Clock): String {
    val currentInstant = clock.now()
    val currentDateTime = currentInstant.toLocalDateTime(timeZone = TimeZone.UTC)
    val alarmInstant = currentInstant
        .minus(currentDateTime.hour, DateTimeUnit.HOUR)
        .minus(currentDateTime.minute, DateTimeUnit.MINUTE)
        .minus(currentDateTime.second, DateTimeUnit.SECOND)
        .plus(alarmHours, DateTimeUnit.HOUR)
        .plus(alarmMinutes, DateTimeUnit.MINUTE)

    val timeLeft: Duration = if (alarmInstant < currentInstant) {
        (alarmInstant.plus(24, DateTimeUnit.HOUR) - currentInstant)
    } else {
        (alarmInstant - currentInstant)
    }
//    println("currentTime")
//    println(currentInstant)
//    println("currentDatetime")
//    println(currentDateTime)
//    println("alarmInstant")
//    println(alarmInstant)
//    println("timeLeft")
//    println(timeLeft)

    return timeLeft.toString()
}

private fun Int.toTimeStringFormat(): String {
    return if (this == 0) {
        "00"
    } else if (this < 10) {
        "0$this"
    } else {
        this.toString()
    }
}