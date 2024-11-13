package org.example.project.alarm.presentations.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.example.project.alarm.presentations.AlarmsAction
import org.example.project.alarm.presentations.AlarmsState
import org.example.project.alarm.presentations.detail.component.AlarmName
import org.example.project.alarm.presentations.detail.component.AlarmNameDialog
import org.example.project.alarm.presentations.detail.component.AlarmTime
import org.example.project.alarm.presentations.detail.component.AlarmTimePickerDialog
import org.example.project.alarm.presentations.model.AlarmUI

@Composable
fun AlarmDetailScreen(
    navController: NavController,
    alarmState: AlarmsState,
    onAction: (action: AlarmsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    var isDialogShown by rememberSaveable {
        mutableStateOf(false)
    }
    var showTimePicker by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .clickable {
                    onAction.invoke(AlarmsAction.OnAlarmClear)
                    navController.popBackStack()
                }
                .wrapContentSize()
                .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Close,
                    tint = Color.White,
                    contentDescription = "Close this screen"
                )
            }

            Button(
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
                modifier = Modifier.clip(RoundedCornerShape(100)),
                onClick = {
                    onAction(
                        AlarmsAction.OnAlarmsCreate
                    )
                }
            ) {
                Text("Save", color = Color.White)
            }
        }
        AlarmTime(
            hour = alarmState.selectedAlarms?.hour ?: 0,
            minutes = alarmState.selectedAlarms?.minute ?: 0,
            description = "Alarm in --",
            onClick = {
                showTimePicker = true
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AlarmName(
            text = alarmState.selectedAlarms?.name.orEmpty(),
            onClick = {
                isDialogShown = true
            }
        )
        if (isDialogShown) {
            AlarmNameDialog(
                name = alarmState.selectedAlarms?.name.orEmpty(),
                onSave = {
                    onAction.invoke(AlarmsAction.UpdateAlarmName(it))
                    isDialogShown = false
                },
                onDismissRequest = {
                    isDialogShown = false
                }
            )
        }
        if (showTimePicker) {
            AlarmTimePickerDialog(
                onDismiss = {
                    showTimePicker = false
                },
                onConfirm = { hour, minute ->
                    onAction.invoke(AlarmsAction.UpdateAlarmTime(hour, minute))
                    showTimePicker = false
                }
            )
        }
    }
}