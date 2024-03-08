package org.example.project

import android.app.Application
import di.sharedModule
import org.example.project.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = androidModule + sharedModule
        startKoin {
            androidContext(this@TestApp)
            modules(modules)
        }
    }
}