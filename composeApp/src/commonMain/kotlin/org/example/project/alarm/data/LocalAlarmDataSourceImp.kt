package org.example.project.alarm.data

import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.alarm.data.tables.AlarmRealmObject
import org.example.project.alarm.domain.AlarmDataSource
import org.example.project.core.data.local_client.RealmDbClient

class LocalAlarmDataSourceImp(
    private val realmDbClient: RealmDbClient
) : AlarmDataSource {
    override suspend fun writeAlarm(alarmRealmObject: AlarmRealmObject) {
        realmDbClient.realm.write {
            copyToRealm(
                instance = alarmRealmObject,
                updatePolicy = UpdatePolicy.ALL
            )
        }
    }

    override suspend fun updateAlarm(alarmRealmObject: AlarmRealmObject) {
        with(realmDbClient.realm) {
            query(AlarmRealmObject::class, "_id = $0", alarmRealmObject._id)
                .first()
                .find()
                .also { alarm ->
                    writeBlocking {
                        if (alarm != null) {
                            findLatest(alarm)?.name = alarmRealmObject.name
                            findLatest(alarm)?.hour = alarmRealmObject.hour
                            findLatest(alarm)?.minute = alarmRealmObject.minute
                            findLatest(alarm)?.isActive = alarmRealmObject.isActive
                        }
                    }
                }
        }
    }

    override fun getAlarms(): Flow<List<AlarmRealmObject>> {
        return realmDbClient.realm
            .query(AlarmRealmObject::class)
            .asFlow()
            .map { results ->
                results.list.toList()
            }
    }

    override suspend fun deleteAlarms(alarmRealmObject: AlarmRealmObject) {
        realmDbClient.realm.writeBlocking {
            val query = this.query(AlarmRealmObject::class, "_id = $0", alarmRealmObject._id)
            delete(query)
        }
    }
}