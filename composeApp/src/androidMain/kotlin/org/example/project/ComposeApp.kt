package org.example.project

import android.app.Application
import di.androidSharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ComposeApp)
            modules(androidSharedModule)
        }
    }
}