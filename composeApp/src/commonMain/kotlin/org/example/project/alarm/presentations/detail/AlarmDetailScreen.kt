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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.example.project.alarm.presentations.AlarmsAction
import org.example.project.alarm.presentations.AlarmsState
import org.example.project.alarm.presentations.model.AlarmUI
import org.example.project.alarm.presentations.model.DisplayableDateTime
import org.jetbrains.compose.resources.vectorResource

@Composable
fun AlarmDetailScreen(
    alarmState: AlarmsState,
    onAction: (action: AlarmsAction) -> Unit,
    modifier: Modifier = Modifier
) {
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
                    /** Trigger action here i.e. close the screen */
                }
                .wrapContentSize()
                .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Close,
                    tint = Color.White,
                    contentDescription = "Close this screen")
            }

            Button(
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
                modifier = Modifier.clip(RoundedCornerShape(100)),
                onClick = {}
            ) {
                Text("Save", color = Color.White)
                onAction(AlarmsAction.OnAlarmsCreate(alarmUI = AlarmUI(
                    name = "test alarm",
                    hourDisplay = DisplayableDateTime(Clock.System.now().toLocalDateTime(TimeZone.UTC), ""),
                    countDownDisplay = DisplayableDateTime(Clock.System.now().toLocalDateTime(TimeZone.UTC), "")
                )))
            }
        }
        Column(
            modifier = Modifier
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
                    text = "16",
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
                    text = "45",
                    style = MaterialTheme.typography.displayLarge
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Alarm in 7h 15min")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Alarm Name")
            Text("Work")
        }
    }
}