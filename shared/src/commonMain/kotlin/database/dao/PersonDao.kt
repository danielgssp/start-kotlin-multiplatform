package com.raywenderlich.database.dao

import com.raywenderlich.MyDatabase
import com.raywenderlich.Person
import com.raywenderlich.database.dto.PersonDto

class PersonDao(database: MyDatabase) {
    private val db = database.personQueries

    fun insert(person: PersonDto) {
        db.insertItem(
            name = person.name,
            genre = person.genre
        )
    }

    fun select(): List<Person> = db.selectAll().executeAsList()
}