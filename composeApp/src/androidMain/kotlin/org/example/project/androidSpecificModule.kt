package org.example.project

import org.example.project.alarm.data.AlarmScheduler
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidSpecificModule = module {

   single<AlarmScheduler> {
      AndroidAlarmSchedulerImp(context = androidContext())
   }
}
