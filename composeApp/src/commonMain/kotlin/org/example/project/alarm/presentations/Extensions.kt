package org.example.project.alarm.presentations

fun Int.toTimeStringFormat(): String {
    return if (this == 0) {
        "00"
    } else if (this < 10) {
        "0$this"
    } else {
        this.toString()
    }
}