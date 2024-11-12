@file:OptIn(ExperimentalMaterial3Api::class)

package org.example.project.alarm.presentations.detail.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun AlarmTimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int, Int) -> Unit
) {
    val state = rememberTimePickerState(
        initialHour = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).hour,
        initialMinute = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).minute,
        is24Hour = true
    )
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(state.hour, state.minute)
                }
            ) {
                Text("OK")
            }
        },
        text = {
            TimePicker(
                state = state,
                modifier = Modifier,
                layoutType = TimePickerLayoutType.Vertical
            )
        }
    )
}