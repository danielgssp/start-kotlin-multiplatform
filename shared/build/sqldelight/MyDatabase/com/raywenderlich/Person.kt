package com.raywenderlich

import kotlin.Long
import kotlin.String

interface Person {
    val id: Long

    val name: String?

    val genre: String?

    data class Impl(
        override val id: Long,
        override val name: String?,
        override val genre: String?
    ) : Person {
        override fun toString(): String = """
        |Person.Impl [
        |  id: $id
        |  name: $name
        |  genre: $genre
        |]
        """.trimMargin()
    }
}
