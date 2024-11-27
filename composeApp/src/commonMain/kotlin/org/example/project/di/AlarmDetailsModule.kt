package org.example.project.di

import org.example.project.alarm.data.AlarmScheduler
import org.example.project.alarm.data.EnableAlarmSound
import org.example.project.alarm.data.LocalAlarmDataSourceImp
import org.example.project.alarm.data.getEnableAlarmSound
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.alarm.presentations.AlarmsViewModel
import org.example.project.core.data.local_client.RealmDbClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val alarmDetailsModule = module {
    factory<AlarmDataSource> {
        LocalAlarmDataSourceImp(get<RealmDbClient>())
    }

    single<EnableAlarmSound> {
        getEnableAlarmSound()
    }

    viewModel {
        AlarmsViewModel(
            get<AlarmDataSource>(),
            get<AlarmScheduler>(),
        )
    }
}