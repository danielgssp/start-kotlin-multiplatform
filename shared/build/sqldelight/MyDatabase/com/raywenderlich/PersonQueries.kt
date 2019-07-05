package com.raywenderlich

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String

interface PersonQueries : Transacter {
    fun <T : Any> selectAll(mapper: (
        id: Long,
        name: String?,
        genre: String?
    ) -> T): Query<T>

    fun selectAll(): Query<Person>

    fun insertItem(name: String?, genre: String?)
}
