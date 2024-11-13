package org.example.project.alarm.data

import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.alarm.data.tables.AlarmTable
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.core.data.local_client.RealmDbClient

class LocalAlarmDataSourceImp(
    private val realmDbClient: RealmDbClient
) : AlarmDataSource {
    override suspend fun writeAlarm(alarmTable: AlarmTable) {
        realmDbClient.realm.write {
            this.copyToRealm(
                instance = alarmTable,
                updatePolicy = UpdatePolicy.ALL
            )
        }
    }

    override suspend fun updateAlarm(alarmTable: AlarmTable) {
        realmDbClient.realm.write {
            val existingAlarm = findLatest(alarmTable)

            if(existingAlarm != null) {
                existingAlarm.name = alarmTable.name
                existingAlarm.hour = alarmTable.hour
                existingAlarm.minute = alarmTable.minute
                existingAlarm.isActive = alarmTable.isActive
                copyToRealm(existingAlarm, updatePolicy = UpdatePolicy.ALL)
            }
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

    override suspend fun deleteAlarms(alarmTable: AlarmTable) {
        realmDbClient.realm.write {
            val latestAlarm = findLatest(alarmTable) ?: return@write
            delete(latestAlarm)
        }
    }
}