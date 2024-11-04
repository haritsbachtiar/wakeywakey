package org.example.project.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.example.project.ui.alarm.detail.AlarmDetailScreen
import org.example.project.ui.alarm.list.AlarmListScreen

@Composable
fun WakeyWakeyApp(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = WakeyWakeyScreen.valueOf(
        backStackEntry?.destination?.route ?: WakeyWakeyScreen.AlarmListScreen.name
    )

    Scaffold(
        topBar = {
            WakeyWakeyAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = WakeyWakeyScreen.AlarmListScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = WakeyWakeyScreen.AlarmListScreen.name) {
                AlarmListScreen()
            }
            composable(route = WakeyWakeyScreen.AlarmDetailScreen.name) {
                AlarmDetailScreen()
            }
            composable(route = WakeyWakeyScreen.AlarmTriggerScreen.name) {

            }
        }
    }
}

@Composable
fun WakeyWakeyAppBar(
    currentScreen: WakeyWakeyScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (currentScreen == WakeyWakeyScreen.AlarmListScreen) {
        TopAppBar(
            title = { Text("Wakey Wakey") },
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                }
            }
        )
    }
}