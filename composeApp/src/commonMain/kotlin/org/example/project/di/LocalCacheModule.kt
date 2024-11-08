package org.example.project.di

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.alarm.data.tables.AlarmTable
import org.example.project.alarm.data.LocalAlarmDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localCacheModule = module {
    single { provideRealm() }
    singleOf(::LocalAlarmDataSource).bind<AlarmDataSource>()
}

fun provideRealm(): Realm {
    val config = RealmConfiguration.Builder(
        schema = setOf(AlarmTable::class)
    ).build()
    return Realm.open(config)
}