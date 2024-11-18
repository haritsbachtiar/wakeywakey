package org.example.project.alarm.presentations.trigger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import wakeywakey.composeapp.generated.resources.Res
import wakeywakey.composeapp.generated.resources.alarm_logo

@Composable
fun AlarmTriggerScreen(
    modifier: Modifier = Modifier,
    alarmTime: String,
    alarmName: String,
    onClick: () -> Unit
) {
   Column(
       modifier = modifier
           .fillMaxSize()
           .background(color = Color.White),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.CenterVertically)
   ) {

       var width by remember {
           mutableStateOf(0)
       }

       Icon(
           painter = painterResource(Res.drawable.alarm_logo),
           contentDescription = "Switch off alarm",
           tint = MaterialTheme.colorScheme.primary
       )

       Text(
           modifier = modifier.wrapContentWidth()
               .onSizeChanged { size ->
                  width = size.width
               },
           textAlign = TextAlign.Center,
           text = alarmTime,
           color = MaterialTheme.colorScheme.primary,
           style = MaterialTheme.typography.displayLarge
       )

       Text(
           modifier = modifier.width(214.dp),
           textAlign = TextAlign.Center,
           text = alarmName.uppercase(),
           color = MaterialTheme.colorScheme.primary,
           style = MaterialTheme.typography.bodyLarge
               .copy(fontWeight = FontWeight.Bold),
       )

       Button(
           modifier = modifier.width(width.dp),
           onClick = onClick
       ) {
           Text(
               text = "Turn Off",
               color = Color.White,
               style = MaterialTheme.typography.bodyLarge
           )
       }
   }
}
@Preview
@Composable
fun AlarmTriggerScreenPreview() {
    AlarmTriggerScreen(
        alarmTime = "12:34",
        alarmName = "Wake Up",
        onClick = { }
    )
}