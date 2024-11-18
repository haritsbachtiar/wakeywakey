@file:OptIn(ExperimentalMaterial3Api::class)

package org.example.project.alarm.presentations.detail.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun AlarmTimePickerDialog(
    initialHour: Int?,
    initialMinute: Int?,
    onDismiss: () -> Unit,
    onConfirm: (Int, Int) -> Unit
) {
    val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val state = rememberTimePickerState(
        initialHour = initialHour ?: currentTime.hour,
        initialMinute = initialMinute ?: currentTime.minute,
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
                modifier = Modifier,
                onClick = {
                    onConfirm(state.hour, state.minute)
                },
                enabled = true,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "OK"
                )
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