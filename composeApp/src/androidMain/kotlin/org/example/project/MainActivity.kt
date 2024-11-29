package org.example.project

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.example.project.core.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            val context = LocalContext.current
            val permissionNotificationLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isPermissionAccepted ->
                /** User has accepted or declined the notification permission */
                println("User choice $isPermissionAccepted")
            }

            LaunchedEffect(key1 = permissionNotificationLauncher) {
                permissionNotificationLauncher.requestWakeyPermission(context)
            }

            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true
            )
        }
    }
}

private fun ActivityResultLauncher<String>.requestWakeyPermission(context: Context) {
    val hasNotificationPermission = context.hasNotificationPermission()

    if (Build.VERSION.SDK_INT >= 33 && !hasNotificationPermission) {
        this.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
}

@Preview
@Composable
fun AlarmListPreview() {
    AppTheme(darkTheme = false, dynamicColor = false) {
//        AlarmDetailScreen(
//            AlarmsState(),
//            {},
//            modifier = Modifier.background(Color.Gray)
//        )
//        AlarmTime(
//            hour = "16",
//            minutes = "45",
//            description = "Alarm in 7h 15 min"
//        )
//        AlarmNameDialog(
//            name = "works",
//            onSave = {},
//            onDismissRequest = { /*TODO*/ }
//        )
//        AlarmTimePickerDialog(
//            initialHour = null,
//            initialMinute = null,
//            onConfirm = { _, _ -> },
//            onDismiss = { }
//        )
        Column {
            Text("12345678")
        }
    }
}