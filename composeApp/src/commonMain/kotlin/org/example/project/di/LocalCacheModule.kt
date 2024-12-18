package org.example.project.di

import org.example.project.alarm.data.LocalAlarmDataSourceImp
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.core.data.local_client.RealmDbClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localCacheModule = module {
    single { RealmDbClient() }
    singleOf(::LocalAlarmDataSourceImp).bind<AlarmDataSource>()
}
