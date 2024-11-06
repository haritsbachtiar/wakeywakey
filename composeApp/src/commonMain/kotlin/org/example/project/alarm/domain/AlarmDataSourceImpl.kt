package org.example.project.alarm.domain

import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.alarm.data.AlarmDataSource
import org.example.project.alarm.data.tables.AlarmTable
import org.example.project.core.data.local_client.RealmDbClient

class AlarmDataSourceImpl(
    private val realmDbClient: RealmDbClient
) : AlarmDataSource {
    override suspend fun writeAlarm(alarmTime: String, alarmName: String) {
        realmDbClient.realm.write {
            this.copyToRealm(
                instance = AlarmTable().apply {
                    this.alarmTime = alarmTime
                    this.name = alarmName
                },
                updatePolicy = UpdatePolicy.ALL
            )
        }
    }

    override fun getAlarms(): Flow<List<AlarmTable>> {
        return realmDbClient.realm
            .query(AlarmTable::class)
            .asFlow()
            .map { results ->
                results.list.toList()
            }
    }
}