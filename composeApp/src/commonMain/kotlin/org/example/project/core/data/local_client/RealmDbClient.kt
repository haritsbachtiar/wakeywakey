package org.example.project.core.data.local_client

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.example.project.alarm.data.tables.AlarmRealmObject

class RealmDbClient {

    var realm: Realm = Realm.open(
        configuration = RealmConfiguration.create(
            schema = setOf(
                AlarmRealmObject::class
            )
        )
    )
}