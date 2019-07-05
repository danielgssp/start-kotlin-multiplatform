package com.raywenderlich.shared

import com.raywenderlich.MyDatabase
import com.raywenderlich.Person
import com.raywenderlich.PersonQueries
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<MyDatabase>.schema: SqlDriver.Schema
    get() = MyDatabaseImpl.Schema

internal fun KClass<MyDatabase>.newInstance(driver: SqlDriver): MyDatabase = MyDatabaseImpl(driver)

private class MyDatabaseImpl(driver: SqlDriver) : TransacterImpl(driver), MyDatabase {
    override val personQueries: PersonQueriesImpl = PersonQueriesImpl(this, driver)

    object Schema : SqlDriver.Schema {
        override val version: Int
            get() = 1

        override fun create(driver: SqlDriver) {
            driver.execute(null, """
                    |CREATE TABLE Person (
                    |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                    |    name TEXT,
                    |    genre TEXT
                    |)
                    """.trimMargin(), 0)
        }

        override fun migrate(
            driver: SqlDriver,
            oldVersion: Int,
            newVersion: Int
        ) {
        }
    }
}

private class PersonQueriesImpl(private val database: MyDatabaseImpl, private val driver: SqlDriver)
        : TransacterImpl(driver), PersonQueries {
    internal val selectAll: MutableList<Query<*>> = copyOnWriteList()

    override fun <T : Any> selectAll(mapper: (
        id: Long,
        name: String?,
        genre: String?
    ) -> T): Query<T> = Query(82, selectAll, driver, """
    |SELECT *
    |FROM Person
    """.trimMargin()) { cursor ->
        mapper(
            cursor.getLong(0)!!,
            cursor.getString(1),
            cursor.getString(2)
        )
    }

    override fun selectAll(): Query<Person> = selectAll(Person::Impl)

    override fun insertItem(name: String?, genre: String?) {
        driver.execute(83, """INSERT OR FAIL INTO Person(name, genre) VALUES (?1, ?2)""", 2) {
            bindString(1, name)
            bindString(2, genre)
        }
        notifyQueries(database.personQueries.selectAll)
    }
}
