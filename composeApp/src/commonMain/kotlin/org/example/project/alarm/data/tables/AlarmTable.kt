package org.example.project.alarm.data.tables

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class AlarmTable: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var alarmTime: String = ""
    var name: String = ""
}