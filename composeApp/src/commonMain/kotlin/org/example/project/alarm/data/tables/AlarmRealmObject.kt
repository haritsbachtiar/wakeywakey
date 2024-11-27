package org.example.project.alarm.data.tables

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class AlarmRealmObject : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var hour: Int = 0
    var minute: Int = 0
    var isActive: Boolean = false
}