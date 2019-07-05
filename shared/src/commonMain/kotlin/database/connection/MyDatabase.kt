package com.raywenderlich.database.connection

import com.raywenderlich.MyDatabase
import com.squareup.sqldelight.db.SqlDriver

fun createDatabase(driver: SqlDriver): MyDatabase = MyDatabase(driver)