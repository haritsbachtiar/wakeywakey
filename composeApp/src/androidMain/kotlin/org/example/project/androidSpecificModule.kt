package org.example.project

import org.example.project.alarm.data.AlarmScheduler
import org.example.project.alarm.data.AndroidAlarmSchedulerImp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val androidSpecificModule = module {

   factory<AlarmScheduler> {
      AndroidAlarmSchedulerImp(context = androidApplication())
   }
}
