package org.example.project

import org.example.project.di.alarmDetailsModule
import org.example.project.di.localCacheModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initializeKoin(koinConfig: KoinAppDeclaration? = null, vararg platformSpecificModules: Module = emptyArray()) {

    startKoin {
        koinConfig?.invoke(this@startKoin)

        this.modules(
            localCacheModule,
            alarmDetailsModule,
            *platformSpecificModules
        )
    }
}