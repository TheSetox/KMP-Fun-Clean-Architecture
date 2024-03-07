package db

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {

    fun createDriver(): SqlDriver
}