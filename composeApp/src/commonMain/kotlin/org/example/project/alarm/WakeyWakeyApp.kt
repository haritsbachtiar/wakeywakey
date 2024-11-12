package org.example.project.alarm

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.example.project.alarm.presentations.AlarmsAction
import org.example.project.alarm.presentations.AlarmsViewModel
import org.example.project.alarm.presentations.WakeyWakeyScreen
import org.example.project.alarm.presentations.detail.AlarmDetailScreen
import org.example.project.alarm.presentations.list.AlarmListScreen
import org.koin.compose.viewmodel.koinViewModel

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

    val alarmsViewModel = koinViewModel<AlarmsViewModel>()
    val alarmState by alarmsViewModel.state.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            WakeyWakeyAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        floatingActionButton = {
            if (currentScreen.name == WakeyWakeyScreen.AlarmListScreen.name) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(WakeyWakeyScreen.AlarmDetailScreen.name)
                    },
                    shape = CircleShape,
                ) {
                    Icon(Icons.Filled.Add, "Large floating action button")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
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
                AlarmListScreen(
                    alarms = listOf(),
                    onCardClick = {
                        navController.navigate(WakeyWakeyScreen.AlarmDetailScreen.name)
                    }
                )
            }
            composable(route = WakeyWakeyScreen.AlarmDetailScreen.name) {
                AlarmDetailScreen(
                    navController = navController,
                    alarmState = alarmState,
                    onAction = { action: AlarmsAction ->
                        alarmsViewModel.onAction(action)
                    }
                )
            }
            composable(route = WakeyWakeyScreen.AlarmTriggerScreen.name) {

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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