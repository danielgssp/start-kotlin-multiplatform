package com.raywenderlich

import com.raywenderlich.database.connection.createDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver

object Database {
    private val driver: SqlDriver = NativeSqliteDriver(MyDatabase.Schema, "MyDatabase.db")
    fun connection(): MyDatabase = createDatabase(driver)
}