package org.example.project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class WakeyWakeyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin(
            koinConfig = {
                androidLogger()
                androidContext(this@WakeyWakeyApplication)
            },
        )
    }
}