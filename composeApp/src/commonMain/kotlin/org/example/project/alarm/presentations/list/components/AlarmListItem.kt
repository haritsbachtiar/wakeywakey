package org.example.project.alarm.presentations.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.alarm.presentations.AlarmsAction
import org.example.project.alarm.presentations.model.AlarmUI

@Composable
fun AlarmListItem(
    alarm: AlarmUI,
    onAction: (AlarmsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            when (value) {
                SwipeToDismissBoxValue.StartToEnd -> false // disable right swipe
                SwipeToDismissBoxValue.EndToStart -> {
                    onAction.invoke(AlarmsAction.OnAlarmsDelete(alarm))
                    true
                }

                SwipeToDismissBoxValue.Settled -> false
            }
        },
        positionalThreshold = { totalDistance ->
            totalDistance * 0.5f // Hold the content in the middle
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier,
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.error, shape = RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onError,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onAction.invoke(AlarmsAction.OnAlarmClick(alarm))
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = alarm.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "${alarm.hour}:${alarm.minute}",
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "---",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Switch(
                    checked = alarm.isActive,
                    onCheckedChange = { isChecked ->
                        onAction.invoke(AlarmsAction.UpdateAlarmStatus(alarm.copy(isActive = isChecked)))
                    }
                )
            }
        }
    }
}