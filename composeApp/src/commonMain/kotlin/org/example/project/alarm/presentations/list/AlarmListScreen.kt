package org.example.project.alarm.presentations.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.alarm.presentations.list.components.AlarmListItem
import org.example.project.alarm.presentations.model.AlarmUI

@Composable
fun AlarmListScreen(
    alarms: List<AlarmUI>,
    onCardClick: (AlarmUI) -> Unit,
    modifier: Modifier = Modifier
) {

    if (alarms.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
        ) {
            items(alarms) {
                AlarmListItem(it, onCardClick)
                if (it != alarms.last()) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "It's empty! Add the first alarm so you\n" +
                        "don't miss an important moment!",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}