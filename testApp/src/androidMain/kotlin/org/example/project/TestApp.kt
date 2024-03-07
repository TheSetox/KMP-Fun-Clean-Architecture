package org.example.project

import android.app.Application
import di.dataModule
import org.example.project.di.androidModule
import org.example.project.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = dataModule + androidModule + domainModule
        startKoin {
            androidContext(this@TestApp)
            modules(modules)
        }
    }
}