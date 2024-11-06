package org.example.project

import android.app.Application

class WakeyWakeyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            koinConfig = {
                androidLogger()
                androidContext(this)
            },
        )
    }
}