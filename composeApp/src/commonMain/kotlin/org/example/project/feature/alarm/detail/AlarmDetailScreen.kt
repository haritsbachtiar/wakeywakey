package org.example.project.feature.alarm.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun AlarmDetailScreen(
    modifier: Modifier = Modifier
) {

   Column(
       modifier = modifier.fillMaxSize()
   ) {
      Row(modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween) {
          IconButton(
              modifier = Modifier.size(48.dp),
              colors = IconButtonDefaults.iconButtonColors(
                  containerColor = Color.LightGray
              ),
              onClick = {}
          ) {
              Icon(
                  imageVector = Icons.Default.Close,
                  contentDescription = "Close the screen",
                  tint = Color.Black
              )
          }

          Button(
              modifier = Modifier.clip(RoundedCornerShape(100)),
              onClick = {}
          ) {
              Text("Save", color = Color.White)
          }
      }
   }
}