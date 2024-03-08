package org.example.project.di

import app.cash.sqldelight.db.SqlDriver
import db.DriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val driverModule = module {
    single<SqlDriver> { DriverFactory(androidContext()).createDriver() }
}


val androidModule = listOf(
    driverModule,
)