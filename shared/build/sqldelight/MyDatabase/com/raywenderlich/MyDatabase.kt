package com.raywenderlich

import com.raywenderlich.shared.newInstance
import com.raywenderlich.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver

interface MyDatabase : Transacter {
    val personQueries: PersonQueries

    companion object {
        val Schema: SqlDriver.Schema
            get() = MyDatabase::class.schema

        operator fun invoke(driver: SqlDriver): MyDatabase = MyDatabase::class.newInstance(driver)}
}
