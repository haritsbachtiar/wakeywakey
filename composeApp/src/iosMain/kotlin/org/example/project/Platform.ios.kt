package org.example.project

import org.example.project.alarm.data.AlarmScheduler
import org.example.project.alarm.data.tables.AlarmRealmObject
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual class AlarmSchedulerImp: AlarmScheduler {
    actual override fun schedule(alarmItem: AlarmRealmObject) {
        TODO("Not yet implemented")
    }

    actual override fun cancel(alarmItem: AlarmRealmObject) {
        TODO("Not yet implemented")
    }
}