package com.raywenderlich

import com.raywenderlich.database.connection.createDatabase
import com.squareup.sqldelight.db.SqlDriver

object Db {
    private var driverRef: SqlDriver? = null
    private var dbRef: MyDatabase? = null

    val ready:Boolean
        get() = driverRef != null

    fun dbSetup(driver: SqlDriver) {
        val db = createDatabase(driver)
        driverRef = driver
        dbRef = db
    }

    internal fun dbClear() {
        driverRef!!.close()
        dbRef = null
        driverRef = null
    }

    val instance: MyDatabase
        get() = dbRef!!
}
