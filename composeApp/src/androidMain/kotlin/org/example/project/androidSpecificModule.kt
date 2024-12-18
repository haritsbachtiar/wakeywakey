package org.example.project

import org.example.project.alarm.data.AlarmScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidSpecificModule = module {

   single<AlarmScheduler> {
      AlarmSchedulerImp(context = androidContext())
   }
}
